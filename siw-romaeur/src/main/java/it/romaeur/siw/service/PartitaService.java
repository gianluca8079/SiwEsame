package it.romaeur.siw.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.romaeur.siw.model.Partita;
import it.romaeur.siw.model.Prestazione;
import it.romaeur.siw.repository.PartitaRepository;

@Service
public class PartitaService {

	@Autowired PartitaRepository partitaRepository;

	public boolean alreadyExists(Partita partita) {
		return partita.getNomeSquadraAvversaria()!=null && partita.getData()!=null && 
				this.partitaRepository.existsByNomeSquadraAvversariaAndData
				(partita.getNomeSquadraAvversaria(), partita.getData());
	}

	public Partita findById(Long idPartita) {
		return this.partitaRepository.findById(idPartita).orElse(null);
	}

	public void addNewPrestazione(Partita partita, Prestazione prestazione) {
		partita.getPrestazioni().add(prestazione);
	}

	public boolean hasNoPrestazioni(Partita partita) {
		return partita.getGiocatoriDellaPartita().isEmpty();
	}

	public void removePrestazioneAssociation(Prestazione prestazione) {
		Partita partita= prestazione.getPartita();
		partita.getPrestazioni().remove(prestazione);
		this.partitaRepository.save(partita);
	}

	public Iterable<Partita> findAll() {
		return this.partitaRepository.findAllByOrderByDataDesc();
	}

	public void save(Partita partita) {
		this.partitaRepository.save(partita);
	}

	public void delete(Partita partita) {
		this.partitaRepository.delete(partita);
	}

	public void createNewPartita(Partita partita, MultipartFile image) {
		try {
			String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
			partita.setStemmaSquadraString(base64Image);
			this.partitaRepository.save(partita);
		}catch(IOException e) {}
	}



}
