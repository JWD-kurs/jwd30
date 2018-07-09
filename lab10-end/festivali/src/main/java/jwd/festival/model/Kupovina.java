package jwd.festival.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Kupovina {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Festival festival;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
		if(festival!=null && !festival.getKupovine().contains(this)){
			festival.getKupovine().add(this);
		}
	}
}
