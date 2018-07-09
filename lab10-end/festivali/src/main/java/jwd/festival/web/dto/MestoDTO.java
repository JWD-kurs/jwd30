package jwd.festival.web.dto;

public class MestoDTO {

	private Long id;
	private String naziv;
	private Integer postanskiKod;
	private String drzava;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getPostanskiKod() {
		return postanskiKod;
	}
	public void setPostanskiKod(Integer postanskiKod) {
		this.postanskiKod = postanskiKod;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}	
}
