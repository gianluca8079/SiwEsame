package it.romaeur.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.romaeur.siw.model.VotoMvp;

public interface VotoMvpRepository extends CrudRepository<VotoMvp,Long>  {

	@Query(value="select giocatore_votato_id "
			+ "from voto_mvp "
			+ "where partita_votata_id = :partitaId "
			+ "GROUP BY giocatore_votato_id "
			+ "ORDER BY COUNT(giocatore_votato_id) DESC "
			+ "limit 1", nativeQuery=true)
	public Long calcolaMvp(@Param("partitaId") Long id);
	
	

}
