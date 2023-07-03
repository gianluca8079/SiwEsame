package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ArtistRepository artistRepository;
	
	@Transactional
	public void updateMovie(Movie movie) {
		movieRepository.save(movie);
		
	}
	
	@Transactional
	public List<Movie> search(String title,Integer year){
		return movieRepository.findByTitleAndYear(title, year);
		
	}

	
	public Movie findMovieById(Long id) {
		return this.movieRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void createNewMovie(Movie movie) {
		this.movieRepository.save(movie);
	}

	@Transactional
	public Iterable<Movie> findAllMovies() {
		return this.movieRepository.findAllOrderByTitle();
	}
	
	@Transactional
	public Movie saveMovie(Movie movie) {
		return this.movieRepository.save(movie);
	}
	
	@Transactional
	public Movie saveDirectorToMovie(Long movieId,long artistId) {
		Movie res = null;
		Artist director = this.artistRepository.findById(artistId).get();
		Movie movie = this.findMovieById(movieId);
		if(movie != null && director != null) {
			res = movie;
		movie.setDirector(director);
		this.saveMovie(movie);
		}
		return res;
		
		
	}
	
	@Transactional
	public List<Movie> findByYear(Integer year) {
		return this.movieRepository.findByYear(year);
	}
	
	@Transactional
	public Movie saveActorToMovie(Long movieId,Long actorId) {
		Movie movie = this.movieRepository.findById(movieId).orElse(null);
		Artist actor = this.artistRepository.findById(actorId).orElse(null);

		if(movie != null && actor != null) {
			Set<Artist> actors = movie.getActors();
			actors.add(actor);
			this.movieRepository.save(movie);
		}
		return movie;

		
		
	}

	@Transactional
	public Movie deleteActorFromMovie(Long movieId,Long actorId) {
		Movie movie = this.movieRepository.findById(movieId).orElse(null);
		Artist actor = this.artistRepository.findById(actorId).orElse(null);
		if(movie != null) {
		Set<Artist> actors = movie.getActors();
		actors.remove(actor);
		this.movieRepository.save(movie);
		}
		return movie;
	}

	@Transactional
	public void addNewRecensione(Movie movie, Recensione recensione) {
		movie.getRecensioni().add(recensione);
		
	}

	public void removeRecensioneFromMovie(Recensione recensione, Long idM) {
		    Movie movie = this.movieRepository.findById(idM).orElse(null);
			movie.getRecensioni().remove(recensione);
			this.movieRepository.save(movie);	
		}

	public List<Movie> findMoviesByArtist(Long id) {
		Artist artist = this.artistRepository.findById(id).orElse(null);
		List<Movie> movies = new ArrayList<>();
		for(Movie m:this.movieRepository.findAll()) {
			if(m.getDirector() != null && m.getDirector().equals(artist) || m.getActors() != null && m.getActors().contains(artist)) {
				movies.add(m);
				
			}
		}
		return movies;
	}		

}
