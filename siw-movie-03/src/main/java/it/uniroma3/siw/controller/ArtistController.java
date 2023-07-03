package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;

@Controller
public class ArtistController {
	
	@Autowired 
	private ArtistService artistService;
	
	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private MovieService movieService;

	@GetMapping(value="/admin/formNewArtist")
	public String formNewArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "admin/formNewArtist.html";
	}
	
	@GetMapping(value="/admin/indexArtist")
	public String indexArtist() {
		return "admin/indexArtist.html";
	}
	
	@PostMapping("/admin/artist")
	public String newArtist(@RequestParam("image") MultipartFile m,@ModelAttribute("artist") Artist artist, Model model) throws IOException {
		if (!artistService.createNewArtist(artist)) {
			String fileName = StringUtils.cleanPath(m.getOriginalFilename());
			artist.setImmagine(fileName);
	        String uploadDir = "src/main/resources/static/images/artists/" + artist.getSurname();
	        FileUploadUtil.saveFile(uploadDir, fileName, m);
	        this.artistRepository.save(artist);
	        model.addAttribute("artist", artist);
			return "artist.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo artista esiste già");
			return "admin/formNewArtist.html"; 
		}
	}

	@GetMapping("/artist/{id}")
	public String getArtist(@PathVariable("id") Long id, Model model) {
		Artist artist = this.artistService.findArtistById(id);
		model.addAttribute("artist", this.artistRepository.findById(id).get());
		model.addAttribute("starredMovies", artist.getStarredMovies());
		model.addAttribute("directedMovies", artist.getDirectedMovies());
		
		return "artist.html";
	}

	@GetMapping("/artist")
	public String getArtists(Model model) {
		model.addAttribute("artists", this.artistRepository.findAll());
		return "artists.html";
	}
	
	@GetMapping(value="/admin/manageArtists")
	public String manageArtists(Model model) {
		model.addAttribute("artists", this.artistService.findAllArtist());
		return "admin/manageArtists.html";
	}
	@GetMapping(value="/admin/formUpdateArtist/{id}")
	public String formUpdateArtist(@PathVariable("id") Long id, Model model) {
		Artist artist = artistService.findArtistById(id);
		if( artist != null) {
		model.addAttribute("artist",artist);
		}
		else {
			return "movieError.html";
		}
		return "admin/formUpdateArtist.html";
	}
	
	@PostMapping(value="/admin/modificaCognome/{artistId}")
	public String modificaCognome(@PathVariable("artistId") Long artistId,@RequestParam String surname, Model model) {
		Artist artist = this.artistService.findArtistById(artistId);
		
		if(artist != null && surname != null) {
			this.artistService.setSurnameToArtist(artistId,surname);
			model.addAttribute("artist", artist);
			return "admin/formUpdateArtist.html";


		}
		else {
		return "movieError.html";
		}
	}
		
		@PostMapping(value="/admin/modificaNome/{artistId}")
		public String modificaNome(@PathVariable("artistId") Long artistId,@RequestParam String name, Model model) {
			Artist artist = this.artistService.findArtistById(artistId);
			
			if(artist != null && name != null) {
				this.artistService.setNameToArtist(artistId,name);
				model.addAttribute("artist", artist);
				return "admin/formUpdateArtist.html";


			}
			else {
			return "movieError.html";
			}
		}
	
	
	
	@Transactional
	@GetMapping("/admin/deleteArtist/{idArtist}")
	public String deleteArtist(@PathVariable ("idArtist") Long idArtist, Model model) {
		Artist artist=this.artistService.findArtistById(idArtist);
		if(artist==null)
			return "artistError.html";
		else {
			this.movieService.removeArtistFromAllMovies(idArtist); 
			this.artistService.removeMoviesArtistDid(idArtist);
			this.artistService.delete(idArtist);
			FileUploadUtil.deleteDirArt(artist.getSurname());
			model.addAttribute("artists", this.artistService.findAllArtist());
			return "admin/manageArtists.html";
		}
	}
}
