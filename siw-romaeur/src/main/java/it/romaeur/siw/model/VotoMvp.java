package it.romaeur.siw.model;

import jakarta.persistence.ManyToOne;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class VotoMvp {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	private Partita partitaVotata;
	
	
	@ManyToOne
	private User utente;
	
	@ManyToOne
	private Giocatore giocatoreVotato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Partita getPartitaVotata() {
		return partitaVotata;
	}

	public void setPartitaVotata(Partita partitaVotata) {
		this.partitaVotata = partitaVotata;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Giocatore getGiocatoreVotato() {
		return giocatoreVotato;
	}

	public void setGiocatoreVotato(Giocatore giocatoreVotato) {
		this.giocatoreVotato = giocatoreVotato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(giocatoreVotato, id, partitaVotata, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VotoMvp other = (VotoMvp) obj;
		return Objects.equals(giocatoreVotato, other.giocatoreVotato) && Objects.equals(id, other.id)
				&& Objects.equals(partitaVotata, other.partitaVotata) && Objects.equals(utente, other.utente);
	}
	
	
	
	

}
