package jwd.festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.festival.model.Mesto;
import jwd.festival.web.dto.MestoDTO;

@Component
public class MestoToMestoDTO 
	implements Converter<Mesto, MestoDTO> {

	@Override
	public MestoDTO convert(Mesto mesto) {
		MestoDTO mestoDTO = new MestoDTO();
		mestoDTO.setId(mesto.getId());
		mestoDTO.setNaziv(mesto.getNaziv());
		mestoDTO.setPostanskiKod(mesto.getPostanskiKod());
		mestoDTO.setDrzava(mesto.getDrzava());
		return mestoDTO;
	}

	public List<MestoDTO> convert(List<Mesto> mesta) {
		List<MestoDTO> ret = new ArrayList<>();
		
		for(Mesto m: mesta){
			ret.add(convert(m));
		}
		
		return ret;
	}
}
