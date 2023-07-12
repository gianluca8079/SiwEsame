package it.romaeur.siw.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.romaeur.siw.model.Giocatore;
import it.romaeur.siw.model.Prestazione;
import it.romaeur.siw.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

	@Autowired private GiocatoreRepository giocatoreRepository;
	
	public Iterable<Giocatore> findAll(){
		return this.giocatoreRepository.findAll();
	}
	public void save(Giocatore giocatore) {
		this.giocatoreRepository.save(giocatore);
	}
	public Giocatore findById(Long id) {
		return this.giocatoreRepository.findById(id).orElse(null);
	}
	public boolean alreadyExists(Giocatore giocatore) {
		return giocatore.getNome()!=null && giocatore.getCognome()!=null && giocatore.getDataDiNascita()!=null
				&& this.giocatoreRepository.existsByNomeAndCognomeAndDataDiNascita
				(giocatore.getNome(), giocatore.getCognome(),giocatore.getDataDiNascita());
	}
	public List<Giocatore> findAllExcept(List<Giocatore> giocatoriDellaPartita) {
		return this.giocatoreRepository.findAllExcept(giocatoriDellaPartita);
	}
	public void addNewPrestazione(Giocatore giocatore, Prestazione prestazione) {
		giocatore.getPrestazioni().add(prestazione);
		
	}
	public void removePrestazioneAssociation(Prestazione prestazione) {
		Giocatore giocatore= prestazione.getGiocatore();
		giocatore.getPrestazioni().remove(prestazione);
		this.giocatoreRepository.save(giocatore);
	}
	public void delete(Giocatore giocatore) {
		this.giocatoreRepository.delete(giocatore);
	}
	public Giocatore update(Long idGiocatore, Giocatore newGiocatore, MultipartFile image) {
		Giocatore giocatore = this.giocatoreRepository.findById(idGiocatore).get();
		giocatore.setNome(newGiocatore.getNome());
		giocatore.setCognome(newGiocatore.getCognome());
		giocatore.setDataDiNascita(newGiocatore.getDataDiNascita());
		giocatore.setNumeroMaglia(newGiocatore.getNumeroMaglia());
		giocatore.setRuolo(newGiocatore.getRuolo());
		if(!image.isEmpty()) {			
			try {
				String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
				giocatore.setImageString(base64Image);
				} catch(IOException e) {}
		}
		this.giocatoreRepository.save(giocatore);
		return giocatore;
	}
	
	@Transactional
	public void createNewGiocatore(Giocatore giocatore, MultipartFile image) {
		try {
			String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
			giocatore.setImageString(base64Image);
			this.giocatoreRepository.save(giocatore);
			} catch(IOException e) {}
	}
	public boolean sameGiocatore(Long idGiocatore, Giocatore newGiocatore) {
		Giocatore giocatore = this.giocatoreRepository.findById(idGiocatore).get();
		return giocatore.getNome().equals(newGiocatore.getNome()) && giocatore.getCognome().equals(newGiocatore.getCognome())
				&& giocatore.getDataDiNascita().equals(newGiocatore.getDataDiNascita());
	}
		
}
