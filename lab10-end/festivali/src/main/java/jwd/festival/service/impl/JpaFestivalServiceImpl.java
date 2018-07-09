package jwd.festival.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.festival.model.Festival;
import jwd.festival.model.Kupovina;
import jwd.festival.repository.FestivalRepository;
import jwd.festival.repository.KupovinaRepository;
import jwd.festival.service.FestivalService;

@Service
@Transactional
public class JpaFestivalServiceImpl implements FestivalService {
	
	@Autowired
	private FestivalRepository festivalRepository;

	@Autowired
	private KupovinaRepository kupovinaRepository;
	
	@Override
	public Page<Festival> findAll(int pageNum) {
		return festivalRepository.findAll(
				new PageRequest(pageNum, 5));
	}

	@Override
	public Festival findOne(Long id) {
		return festivalRepository.findOne(id);
	}

	@Override
	public void save(Festival festival) {
		festivalRepository.save(festival);
	}

	@Override
	public void remove(Long id) {
		festivalRepository.delete(id);
	}

	@Override
	public Kupovina buy(Long id) {
		
		Festival f = findOne(id);
		
		Kupovina k = null;
		if(f.getKolicina() > 0) {
			k = new Kupovina();
			k.setFestival(f);
			kupovinaRepository.save(k);
			
			f.setKolicina(f.getKolicina() - 1);
			festivalRepository.save(f);
		}
		
		return k;
	}
	
	@Override
	public Page<Festival> findByMestoId(int pageNum, Long mestoId) {
		
		return festivalRepository.findByMestoId(mestoId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Festival> pretraga(String naziv, Double maxCena, Long idMesta, int page) {
		if(naziv != null ){
			naziv = "%" + naziv + "%";
		}
		
		return festivalRepository.pretraga(naziv, maxCena, idMesta, new PageRequest(page, 5));
	}

}
