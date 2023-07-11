package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.DuplicateRecensioneValidator;
import it.uniroma3.siw.controller.validator.RecensioneValidator;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UserService;

@Controller
public class RecensioneController {

	@Autowired 
	private RecensioneService recensioneService;

	@Autowired 
	private MovieService movieService;

	


	@Autowired
	private UserService userService;

	@Autowired
	private RecensioneValidator RecensioneValidator;
	
	@Autowired
	private DuplicateRecensioneValidator duplicateRecensioneValidator;

	@GetMapping(value="/formNewRecensione/{idMovie}")
	public String formNewRecensione(@PathVariable("idMovie") Long idMovie,Model model) {
		Movie movie = this.movieService.findMovieById(idMovie);
		model.addAttribute("movie",movie);
		model.addAttribute("recensione", new Recensione());
		return "formNewRecensione.html";
	}


	@PostMapping(value="/recensione/{idMovie}")
	public String newRecensione(@Valid @ModelAttribute("recensione") Recensione recensione,BindingResult bindingResult, @PathVariable ("idMovie") Long idMovie, Model model) throws IOException{

		 Movie movie = this.movieService.findMovieById(idMovie);
		this.duplicateRecensioneValidator.validate(this.movieService.findMovieById(idMovie), bindingResult);
		this.RecensioneValidator.validate(recensione, bindingResult);
		if (!bindingResult.hasErrors()) {
		

		User u = userService.getCurrentUser();
		this.userService.addRecensione(u,recensione);
		this.movieService.addNewRecensione(movie,recensione);
		this.recensioneService.createNewRecensione(recensione,movie,u);
		model.addAttribute("utente",u);
		model.addAttribute("recensione", recensione);
		model.addAttribute("movie", movie);
		model.addAttribute("recensioni",movie.getRecensioni());
		return "movie.html";
		}
		else {
			model.addAttribute("movie", movie);
			return "formNewRecensione.html";
		}
	} 
	
	@GetMapping("/admin/deleteRecensione/{idRecensione}")
	public String cancellaRecensione(@PathVariable ("idRecensione") Long idR, Model model) {
		Recensione recensione=this.recensioneService.findRecensioneById(idR);
		if(recensione==null)
			return "recensioneError.html";
		Movie movie=recensione.getFilmRecensito();
		this.movieService.removeRecensioneFromMovie(recensione,movie.getId());
		this.userService.removeRecensioneFromUtente(recensione);
		this.recensioneService.delete(idR);
		model.addAttribute("movie",movie);
		model.addAttribute("recensioni",movie.getRecensioni());	
		return "movie.html";
	}
}


