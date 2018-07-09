package jwd.festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.festival.model.Mesto;

@Repository
public interface MestoRepository 
	extends JpaRepository<Mesto, Long> {

}
