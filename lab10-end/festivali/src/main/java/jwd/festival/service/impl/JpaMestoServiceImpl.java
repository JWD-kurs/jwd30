package jwd.festival.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.festival.model.Mesto;
import jwd.festival.repository.MestoRepository;
import jwd.festival.service.MestoService;

@Service
@Transactional
public class JpaMestoServiceImpl implements MestoService {
	@Autowired
	private MestoRepository mestoRepository;

	@Override
	public List<Mesto> findAll() {
		return mestoRepository.findAll();
	}

	@Override
	public Mesto findOne(Long id) {
		return mestoRepository.findOne(id);
	}

	@Override
	public void save(Mesto mesto) {
		mestoRepository.save(mesto);
	}

	@Override
	public void remove(Long id) {
		mestoRepository.delete(id);
	}

	
	
}
