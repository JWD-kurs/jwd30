package jwd.festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.festival.model.Festival;
import jwd.festival.web.dto.FestivalDTO;

@Component
public class FestivalToFestivalDTO 
	implements Converter<Festival, FestivalDTO> {

	@Override
	public FestivalDTO convert(Festival source) {
		FestivalDTO dto = new FestivalDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setOrganizator(source.getOrganizator());
		dto.setDatumPocetka(source.getDatumPocetka());
		dto.setCenaKarte(source.getCenaKarte());
		dto.setKolicina(source.getKolicina());
		dto.setMestoId(source.getMesto().getId());
		dto.setMestoNaziv(source.getMesto().getNaziv());
		
		return dto;
	}
	
	public List<FestivalDTO> convert(List<Festival> festivali){
		List<FestivalDTO> ret = new ArrayList<>();
		
		for(Festival f : festivali){
			ret.add(convert(f));
		}
		
		return ret;
	}

}
