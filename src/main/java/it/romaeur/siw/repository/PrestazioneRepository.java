package it.romaeur.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.romaeur.siw.model.Prestazione;

public interface PrestazioneRepository extends CrudRepository<Prestazione,Long>{
	
	@Query(value = "select *" 
	+  " from Prestazione p"
	+ " where p.giocatore_id = :giocatore_id ",nativeQuery=true)
    public List<Prestazione> findAllByIdGiocatore(@Param("giocatore_id") Long giocatore_id);

}
