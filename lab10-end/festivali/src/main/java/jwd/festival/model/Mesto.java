package jwd.festival.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Mesto {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(unique=true, nullable=false)
	private String naziv;
	@Column
	private int postanskiKod;
	@Column
	private String drzava;	
	@OneToMany(mappedBy="mesto",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Festival> festivali = new ArrayList<>();
	
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
	public void setPostanskiKod(int postanskiKod) {
		this.postanskiKod = postanskiKod;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public List<Festival> getFestivali() {
		return festivali;
	}
	public void setFestivali(List<Festival> festivali) {
		this.festivali = festivali;
	}
	public void addFestival(Festival festival){
		this.festivali.add(festival);
		
		if(!this.equals(festival.getMesto())){
			festival.setMesto(this);
		}
	}
}
