package it.romaeur.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prestazione {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Min(0)
	private int punti;
	@NotNull
	@Min(0)
	private int assist;
	@NotNull
	@Min(0)
	private int rimbalzi;
	@NotNull
	@Min(0)
	private int rubate;
	@NotNull
	@Min(0)
	private int stoppate;
	@ManyToOne 
	private Giocatore giocatore;
	@ManyToOne 
	private Partita partita;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public int getAssist() {
		return assist;
	}
	public void setAssist(int assist) {
		this.assist = assist;
	}
	public int getRimbalzi() {
		return rimbalzi;
	}
	public void setRimbalzi(int rimbalzi) {
		this.rimbalzi = rimbalzi;
	}
	public int getRubate() {
		return rubate;
	}
	public void setRubate(int rubate) {
		this.rubate = rubate;
	}
	public int getStoppate() {
		return stoppate;
	}
	public void setStoppate(int stoppate) {
		this.stoppate = stoppate;
	}
	public Giocatore getGiocatore() {
		return giocatore;
	}
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	public Partita getPartita() {
		return partita;
	}
	public void setPartita(Partita partita) {
		this.partita = partita;
	}
	@Override
	public int hashCode() {
		return Objects.hash(assist, giocatore, id, partita, punti, rimbalzi, rubate, stoppate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestazione other = (Prestazione) obj;
		return assist == other.assist && Objects.equals(giocatore, other.giocatore) && Objects.equals(id, other.id)
				&& Objects.equals(partita, other.partita) && punti == other.punti && rimbalzi == other.rimbalzi
				&& rubate == other.rubate && stoppate == other.stoppate;
	}
	
	
	
	
}
