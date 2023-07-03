package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepository;

	@Transactional
	public boolean createNewArtist(Artist artist) {
		boolean res = false;
		if(!artistRepository.existsByNameAndSurname(artist.getName(), artist.getSurname())) {
			this.artistRepository.save(artist);
		}
		
		return res;
	}
	
	@Transactional
	public Iterable<Artist> findAllArtist(){
		return this.artistRepository.findAll();
	}
	
	public Artist findArtistById(Long idArtist) {
		return this.artistRepository.findById(idArtist).orElse(null);
	}
	
	@Transactional
	public List<Artist> findActorsNotInMovie(Long movieId){
		List<Artist> actorsToAdd = new ArrayList<>();

		for (Artist a : artistRepository.findActorsNotInMovie(movieId)) {
			actorsToAdd.add(a);
		}
		return actorsToAdd;
	}
	
	@Transactional
	public void removeMoviesFromActor(Movie movie) {
		Iterable<Artist> actors=this.artistRepository.findActorsInMovie(movie.getId());
		for(Artist actor:actors) {
			actor.getStarredMovies().remove(movie);
			this.artistRepository.save(actor);
		}
	}
	
	public void removeMoviesArtistDid(Long idActor) {
		Artist artist= this.artistRepository.findById(idActor).get();
		artist.setDirectedMovies(null);
		artist.setStarredMovies(null);
		this.artistRepository.save(artist);
}
	
	public void delete(Long idArtist) {
		Artist artist= this.artistRepository.findById(idArtist).get();
		this.artistRepository.delete(artist);
	}
}
