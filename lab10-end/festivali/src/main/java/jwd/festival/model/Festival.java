package jwd.festival.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Festival {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(unique=true, nullable=false)
	private String naziv;
	@Column(nullable=false)
	private String organizator;
	@Column
	private String datumPocetka;
	@Column(nullable=false)
	private Double cenaKarte;
	@Column(nullable=false)
	private Integer kolicina;
	@ManyToOne(fetch=FetchType.EAGER)
	private Mesto mesto;
	@OneToMany(mappedBy="festival",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Kupovina> kupovine = new ArrayList<>();
	
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
	public String getOrganizator() {
		return organizator;
	}
	public void setOrganizator(String organizator) {
		this.organizator = organizator;
	}
	public String getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(String datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public Double getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(Double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
		if(mesto!=null && !mesto.getFestivali().contains(this)){
			mesto.getFestivali().add(this);
		}
	}
	public List<Kupovina> getKupovine() {
		return kupovine;
	}
	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	public void addKupovina(Kupovina kupovina){
		this.kupovine.add(kupovina);
		
		if(!this.equals(kupovina.getFestival())){
			kupovina.setFestival(this);
		}
	}
	
}
