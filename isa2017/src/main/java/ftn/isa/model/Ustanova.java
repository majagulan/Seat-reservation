package ftn.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Ustanova implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column
	private String promotivniOpis;
	
	@Enumerated(EnumType.STRING)
	private UstanovaTip ustanovaTip;
	
	public Ustanova() {
		
	}

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPromotivniOpis() {
		return promotivniOpis;
	}

	public void setPromotivniOpis(String promotivniOpis) {
		this.promotivniOpis = promotivniOpis;
	}

	public UstanovaTip getUstanovaTip() {
		return ustanovaTip;
	}

	public void setUstanovaTip(UstanovaTip ustanovaTip) {
		this.ustanovaTip = ustanovaTip;
	}
	
	
	
}
