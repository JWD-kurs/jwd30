package jwd.festival.service;

import java.util.List;

import jwd.festival.model.Mesto;


public interface MestoService {
	List<Mesto> findAll();
	Mesto findOne(Long id);
	void save(Mesto sajam);
	void remove(Long id);

}
