package ftn.isa.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Karta implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private double originalnaCena;
	
	@Column
	private Date datum;
	
	@Column
	private Time vreme;
	
	@Column
	private int popust;
	
	public Karta() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOriginalnaCena() {
		return originalnaCena;
	}

	public void setOriginalnaCena(double originalnaCena) {
		this.originalnaCena = originalnaCena;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Time getVreme() {
		return vreme;
	}

	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}
	
	

}
