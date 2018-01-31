package ftn.isa.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Ponuda implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private double cenaPonude;
	
	@Column
	private Date datumPonude;
	
	public Ponuda() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getNaziv() {
		return cenaPonude;
	}

	public void setNaziv(double naziv) {
		this.cenaPonude = naziv;
	}

	public Date getDatumPonude() {
		return datumPonude;
	}

	public void setDatumPonude(Date datumPonude) {
		this.datumPonude = datumPonude;
	}
	
	

}
