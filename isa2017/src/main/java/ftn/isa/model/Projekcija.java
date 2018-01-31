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
public class Projekcija implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String imeReditelja;
	
	@Column
	private int trajanje;
	
	@Column
	private String poster;
	
	@Column
	private double prosecnaOcena;
	
	@Column
	private String opis;
	
	@Column
	private int decimalnaCena;
	
	@Column
	private int popust;
	
	@Enumerated(EnumType.STRING)
	private Zanr zanr;
	
	public Projekcija() {
		
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

	public String getImeReditelja() {
		return imeReditelja;
	}

	public void setImeReditelja(String imeReditelja) {
		this.imeReditelja = imeReditelja;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getDecimalnaCena() {
		return decimalnaCena;
	}

	public void setDecimalnaCena(int decimalnaCena) {
		this.decimalnaCena = decimalnaCena;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}
	
	
	 

}
