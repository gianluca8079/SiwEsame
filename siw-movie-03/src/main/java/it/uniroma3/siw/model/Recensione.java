package it.uniroma3.siw.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Recensione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String title;
	
    @Min(1)
    @Max(5)	
	private int valutazione;
	
	@Column(length = 5000)
	private String testo;
	
	@ManyToOne
	private Movie filmRecensito;
	
	@ManyToOne
	private User utente;

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Movie getFilmRecensito() {
		return filmRecensito;
	}

	public void setFilmRecensito(Movie filmRecensito) {
		this.filmRecensito = filmRecensito;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, testo, title, valutazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(id, other.id) && Objects.equals(testo, other.testo) && Objects.equals(title, other.title)
				&& valutazione == other.valutazione;
	}

	

	
	
	

}
