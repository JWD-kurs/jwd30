package jwd.festival.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.festival.model.Festival;

@Repository
public interface FestivalRepository 
	extends JpaRepository<Festival, Long> {

	Page<Festival> findByMestoId(Long mestoId, Pageable pageRequest);

	@Query("SELECT f FROM Festival f WHERE "
			+ "(:naziv IS NULL or f.naziv like :naziv ) AND "
			+ "(:maxCena IS NULL OR f.cenaKarte <= :maxCena) AND "
			+ "(:idMesta IS NULL or f.mesto.id = :idMesta )"
			)
	Page<Festival> pretraga(
			@Param("naziv") String naziv, 
			@Param("maxCena") Double maxCena, 
			@Param("idMesta") Long idMesta,
			Pageable pageRequest);

}
