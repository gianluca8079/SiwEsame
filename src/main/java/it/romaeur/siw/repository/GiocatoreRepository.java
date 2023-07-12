package it.romaeur.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.romaeur.siw.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore,Long>{

	public boolean existsByNomeAndCognomeAndDataDiNascita(String nome, String cognome, LocalDate dataDiNascita);
	
	@Query("SELECT o FROM Giocatore o WHERE o NOT IN :giocatoriDaEsculedere")
    public List<Giocatore> findAllExcept(@Param("giocatoriDaEsculedere") List<Giocatore> giocatoriDaEsculedere);
	
}
