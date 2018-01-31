package ftn.isa.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class TematskiRekvizit implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String opis;
	
	@Column
	private Date rokPonuda;
	
	@Column
	private String slika;
	
	@Column
	private double originalnaCena;
	
	public TematskiRekvizit() {
		
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getRokPonuda() {
		return rokPonuda;
	}

	public void setRokPonuda(Date rokPonuda) {
		this.rokPonuda = rokPonuda;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public double getOriginalnaCena() {
		return originalnaCena;
	}

	public void setOriginalnaCena(double originalnaCena) {
		this.originalnaCena = originalnaCena;
	}
	
	
	


}
