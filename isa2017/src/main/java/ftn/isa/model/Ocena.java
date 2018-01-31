package ftn.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Ocena implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private int ocenaProjekcije;
	
	@Column
	private int ocenaUstanove;
	
	public Ocena() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOcenaProjekcije() {
		return ocenaProjekcije;
	}

	public void setOcenaProjekcije(int ocenaProjekcije) {
		this.ocenaProjekcije = ocenaProjekcije;
	}

	public int getOcenaUstanove() {
		return ocenaUstanove;
	}

	public void setOcenaUstanove(int ocenaUstanove) {
		this.ocenaUstanove = ocenaUstanove;
	}
	
	

}
