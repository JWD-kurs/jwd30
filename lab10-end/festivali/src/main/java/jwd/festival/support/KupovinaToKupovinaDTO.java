package jwd.festival.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.festival.model.Kupovina;
import jwd.festival.web.dto.KupovinaDTO;

@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO> {

	@Override
	public KupovinaDTO convert(Kupovina source) {
		KupovinaDTO dto = new KupovinaDTO();
		dto.setId(source.getId());
		return dto;
	}
}
