package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	public List<Movie> findByYear(int year);

	public boolean existsByTitleAndYear(String title, int year);	
	
	public List<Movie> findByTitleAndYear(String title,int year);
	
	@Query(value="select * "
			+ "from movie m "
			+ "order by m.title ", nativeQuery=true)
	public Iterable<Movie> findAllOrderByTitle();

	
	

	
	
	@Query(value="select * "
			+ "from movie m "
			+ "where director_id = :artistId ", nativeQuery=true)
	public List<Movie> findAllByDirector(@Param("artistId") Long id);
	

	
	
	

}