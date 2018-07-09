package jwd.festival.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.festival.model.Festival;
import jwd.festival.model.Mesto;
import jwd.festival.service.FestivalService;
import jwd.festival.service.MestoService;
import jwd.festival.support.FestivalToFestivalDTO;
import jwd.festival.support.MestoToMestoDTO;
import jwd.festival.web.dto.FestivalDTO;
import jwd.festival.web.dto.MestoDTO;

@RestController
@RequestMapping(value="/api/mesta")
public class ApiMestoController {
	@Autowired
	private MestoService mestaService;
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private MestoToMestoDTO toDTO;
	@Autowired
	private FestivalToFestivalDTO toFestivalDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MestoDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(mestaService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MestoDTO> get(
			@PathVariable Long id){
		
		Mesto mesto = mestaService.findOne(id);
		
		if(mesto == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(mesto),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{mestoId}/festivali")
	public ResponseEntity<List<FestivalDTO>> festivaliMesta(
			@PathVariable Long mestoId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Festival> festivali = festivalService.findByMestoId(pageNum, mestoId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(festivali.getTotalPages()) );
		return  new ResponseEntity<>(
				toFestivalDTO.convert(festivali.getContent()),
				headers,
				HttpStatus.OK);
	}
}
