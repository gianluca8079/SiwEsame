package it.romaeur.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;

import java.util.ArrayList; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Giocatore {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDiNascita;
	@NotNull
	private String ruolo; 	
	@NotNull
	@Min(0)
	@Max(99)
	private int numeroMaglia;

	@Column(length=10000000)
	private String imageString;
	
	@OneToMany(mappedBy= "giocatore",cascade= {CascadeType.REMOVE})
	private List<Prestazione> prestazioni;
	
	@OneToMany(mappedBy="giocatoreVotato")
	private List<VotoMvp> voti;
	
	public Giocatore(){
		this.prestazioni= new ArrayList<Prestazione>();
	}
	
	
	public String getImageString() {
		return imageString;
	}


	public void setImageString(String imageString) {
		this.imageString = imageString;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public int getNumeroMaglia() {
		return numeroMaglia;
	}
	public void setNumeroMaglia(int numeroMaglia) {
		this.numeroMaglia = numeroMaglia;
	}
	public List<Prestazione> getPrestazioni() {
		return prestazioni;
	}
	public void setPrestazioni(List<Prestazione> prestazioni) {
		this.prestazioni = prestazioni;
	}
	
	
	public List<VotoMvp> getVoti() {
		return voti;
	}


	public void setVoti(List<VotoMvp> voti) {
		this.voti = voti;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataDiNascita, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Giocatore other = (Giocatore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataDiNascita, other.dataDiNascita)
				&& Objects.equals(nome, other.nome);
	}
	
	
	public int getPartiteGiocate() {
		return this.prestazioni.size();
	}
	
	public float getMediaPunti() {
		int sommaPunti = 0;
		float partiteGiocate = this.getPartiteGiocate();
		for(Prestazione p:prestazioni) {
			sommaPunti += p.getPunti();
		}
		if(this.getPartiteGiocate() == 0)
			return 0;
		else
		return sommaPunti/partiteGiocate; 
	}
	
	public float getMediaAssist() {
		int sommaAssist = 0;
		float partiteGiocate = this.getPartiteGiocate();
		for(Prestazione p:prestazioni) {
			sommaAssist += p.getAssist();
		}
		if(this.getPartiteGiocate() == 0)
			return 0;
		else
		return sommaAssist/partiteGiocate; 
	}
	
	public float getMediaRimbalzi() {
		int sommaRimbalzi = 0;
		float partiteGiocate = this.getPartiteGiocate();
		for(Prestazione p:prestazioni) {
			sommaRimbalzi += p.getRimbalzi();
		}
		if(this.getPartiteGiocate() == 0)
			return 0;
		else
		return sommaRimbalzi/partiteGiocate; 	}
	
	public float getMediaRubate() {
		int sommaRubate = 0;
		float partiteGiocate = this.getPartiteGiocate();
		for(Prestazione p:prestazioni) {
			sommaRubate += p.getRubate();
		}
		if(this.getPartiteGiocate() == 0)
			return 0;
		else
		return sommaRubate/partiteGiocate; 
	}
	
	public float getMediaStoppate() {
		int sommaStoppate = 0;
		float partiteGiocate = this.getPartiteGiocate();
		for(Prestazione p:prestazioni) {
			sommaStoppate += p.getStoppate();
		}
		if(this.getPartiteGiocate() == 0)
			return 0;
		else
		return sommaStoppate/partiteGiocate; 	}
	
	
	
	
}
