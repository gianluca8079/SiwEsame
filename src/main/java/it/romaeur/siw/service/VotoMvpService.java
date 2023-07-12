package it.romaeur.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.romaeur.siw.model.Giocatore;
import it.romaeur.siw.model.Partita;
import it.romaeur.siw.model.User;
import it.romaeur.siw.model.VotoMvp;
import it.romaeur.siw.repository.VotoMvpRepository;

@Service
public class VotoMvpService {

	@Autowired
	private PartitaService partitaService;
	
	@Autowired
	private VotoMvpRepository votoMvpRepository;
	
	@Autowired
	private GiocatoreService giocatoreService;

	public Giocatore calcolaMvp(Long idPartita) {
		Long idGiocatore = this.votoMvpRepository.calcolaMvp(idPartita);
		if(idGiocatore != null)
		return this.giocatoreService.findById(idGiocatore);
		return null;
		
		
		
	}

	public void createNewVotoMvp(VotoMvp voto, User user, Partita partita, Giocatore giocatore) {
		voto.setUtente(user);
		user.getVoti().add(voto);
		voto.setPartitaVotata(partita);
		partita.getVoti().add(voto);
		voto.setGiocatoreVotato(giocatore);
		giocatore.getVoti().add(voto);
		this.votoMvpRepository.save(voto);		
	}

	public boolean alreadyExists(VotoMvp voto) {
		return voto.getPartitaVotata()!=null && voto.getGiocatoreVotato()!=null && 
				voto.getUtente()!=null;
				

}
}
