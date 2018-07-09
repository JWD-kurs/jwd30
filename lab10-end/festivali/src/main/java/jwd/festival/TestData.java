package jwd.festival;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.festival.model.Festival;
import jwd.festival.model.Mesto;
import jwd.festival.service.FestivalService;
import jwd.festival.service.MestoService;

@Component
public class TestData {
	@Autowired
	private MestoService mestoService;
	@Autowired
	private FestivalService festivalService;

	@PostConstruct
	public void init() {
		
		Mesto subotica = new Mesto();
		subotica.setDrzava("Srbija");
		subotica.setNaziv("Subotica");
		subotica.setPostanskiKod(24000);
		mestoService.save(subotica);
		
		Festival festival1 = new Festival();
		festival1.setNaziv("Summer3p");
		festival1.setCenaKarte(1230.99);
		festival1.setKolicina(99);
		festival1.setDatumPocetka("11.7.2018.");
		festival1.setOrganizator("Grad Subotica");
		festival1.setMesto(subotica);
		festivalService.save(festival1);

		Mesto noviSad = new Mesto();
		noviSad.setDrzava("Srbija");
		noviSad.setNaziv("Novi Sad");
		noviSad.setPostanskiKod(21000);
		mestoService.save(noviSad);
		
		Festival festival2 = new Festival();
		festival2.setNaziv("EXIT");
		festival2.setCenaKarte(6000.0);
		festival2.setKolicina(99);
		festival2.setDatumPocetka("10.7.2018.");
		festival2.setOrganizator("Exit fondacija");
		festival2.setMesto(noviSad);
		festivalService.save(festival2);
		
		Festival festival3 = new Festival();
		festival3.setNaziv("Festival vina");
		festival3.setCenaKarte(200.0);
		festival3.setKolicina(99);
		festival3.setDatumPocetka("11.7.2018.");
		festival3.setOrganizator("Grad Novi Sad");
		festival3.setMesto(noviSad);
		festivalService.save(festival3);


	}
}