package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {

	@Query(value="select * "
			+ "from recensione r "
			+ "where r.film_recensito_id = :movieId"
			+ " and r.utente_id = :userId", nativeQuery=true)
	public Recensione existsFilmRecensitoIdAndUtenteId(@Param("movieId") Long idM,@Param("userId") Long idU); 
		
	
	public boolean existsByTitleAndUtente(String title, String utente);	
	
	

	public List<Recensione> findAllByFilmRecensito(Movie movie);



}
