package jwd.festival.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.festival.model.Festival;
import jwd.festival.model.Kupovina;
import jwd.festival.service.FestivalService;
import jwd.festival.support.FestivalDTOToFestival;
import jwd.festival.support.FestivalToFestivalDTO;
import jwd.festival.support.KupovinaToKupovinaDTO;
import jwd.festival.web.dto.FestivalDTO;
import jwd.festival.web.dto.KupovinaDTO;

@RestController
@RequestMapping("/api/festivali")
public class ApiFestivalController {
	@Autowired
	private FestivalService festivalService;
	@Autowired
	private FestivalToFestivalDTO toDTO;
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;
	@Autowired
	private FestivalDTOToFestival toFestival;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<FestivalDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Double maxCena,
			@RequestParam(required=false) Long idMesta,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Festival> festivali;
		
		if(naziv != null || idMesta != null || maxCena != null) {
			festivali = festivalService.pretraga(naziv, maxCena, idMesta, pageNum);
		}else{
			festivali = festivalService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(festivali.getTotalPages()) );
		return  new ResponseEntity<>(
				toDTO.convert(festivali.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<FestivalDTO> get(
			@PathVariable Long id){
		Festival festival = festivalService.findOne(id);
		
		if(festival==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(festival),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FestivalDTO> add(
			@Validated @RequestBody FestivalDTO noviFestival){
		
		Festival festival = toFestival.convert(noviFestival); 
		festivalService.save(festival);
		
		return new ResponseEntity<>(toDTO.convert(festival),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}")
	public ResponseEntity<KupovinaDTO> buy(@PathVariable Long id){
		 
		Kupovina k = festivalService.buy(id);
		
		return new ResponseEntity<>(toKupovinaDTO.convert(k),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<FestivalDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody FestivalDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Festival festival = toFestival.convert(izmenjen); 
		festivalService.save(festival);
		
		return new ResponseEntity<>(toDTO.convert(festival),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<FestivalDTO> delete(@PathVariable Long id){
		festivalService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
