package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.RecensioneRepository;

@Service
public class RecensioneService {
	
	@Autowired RecensioneRepository recensioneRepository;

	@Transactional
	public void createNewRecensione(Recensione recensione, Movie movie, User u) {
		recensione.setFilmRecensito(movie);
		recensione.setUtente(u);
		this.recensioneRepository.save(recensione);

		
	}

	@Transactional
	public boolean existsFilmRecensitoIdAndUtenteId(Long idM, Long idU) {
		if(this.recensioneRepository.existsFilmRecensitoIdAndUtenteId(idM,idU) != null)
			return true;
					return false;
	}
	
	public Recensione findRecensioneById(Long id) {
		return this.recensioneRepository.findById(id).orElse(null);
	}

	public void delete(Long idR) {
		Recensione recensione= this.findRecensioneById(idR);
		this.recensioneRepository.delete(recensione);		
	}
	
	@Transactional
	public void removeMovieAssociationFromReview(Movie movie) {
		List<Recensione> recensioni= this.recensioneRepository.findAllByFilmRecensito(movie);
		for(Recensione recensione:recensioni) {
			recensione.setFilmRecensito(null);
			this.recensioneRepository.delete(recensione);
		}
	}
	

}
