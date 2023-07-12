package it.romaeur.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Partita {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Min(0)
	private int punteggioA;
	@NotNull
	@Min(0)
	private int punteggioB;
	@NotBlank
	private String nomeSquadraAvversaria;
	
	@Column(length=10000000)
	private String stemmaSquadraString;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@OneToMany(mappedBy="partita",
			cascade= {CascadeType.REMOVE})
	private List<Prestazione>prestazioni;
	
	
	@OneToMany(mappedBy="partitaVotata")
	private List<VotoMvp> voti;
	
	
	
	
	
	public Partita() {
		this.prestazioni = new ArrayList<>();
	}

	public String getStemmaSquadraString() {
		return stemmaSquadraString;
	}

	public void setStemmaSquadraString(String stemmaSquadraString) {
		this.stemmaSquadraString = stemmaSquadraString;
	}

	public List <Giocatore>getGiocatoriDellaPartita() {
		ArrayList<Giocatore> giocatoriDellaPartita= new ArrayList<Giocatore>();
		for(Prestazione p : this.getPrestazioni())
			giocatoriDellaPartita.add(p.getGiocatore());
		return giocatoriDellaPartita;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPunteggioA() {
		return punteggioA;
	}
	public void setPunteggioA(int punteggioA) {
		this.punteggioA = punteggioA;
	}
	public int getPunteggioB() {
		return punteggioB;
	}
	public void setPunteggioB(int punteggioB) {
		this.punteggioB = punteggioB;
	}
	public String getNomeSquadraAvversaria() {
		return nomeSquadraAvversaria;
	}
	public void setNomeSquadraAvversaria(String nomeSquadraAvversaria) {
		this.nomeSquadraAvversaria = nomeSquadraAvversaria;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
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
		return Objects.hash(data, nomeSquadraAvversaria, punteggioA, punteggioB);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partita other = (Partita) obj;
		return Objects.equals(data, other.data) && Objects.equals(nomeSquadraAvversaria, other.nomeSquadraAvversaria)
				&& punteggioA == other.punteggioA && punteggioB == other.punteggioB;
	}
	
	
	
}
