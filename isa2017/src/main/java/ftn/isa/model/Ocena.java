package ftn.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ftn.isa.model.korisnici.Posetilac;

@Entity
@Table(name = "OCENA")
public class Ocena implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8335307552739844560L;

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Pogledano pogledano;
	
	@ManyToOne
	private Posetilac posetilac;
	
	@ManyToOne
	private Ustanova ustanova;


	@Column
	private double ocenaProjekcije;

	@Column
	private double ocenaUstanove;

	public Ocena() {

	}
	
	public Ocena(double ocenaProjekcije,double ocenaUstanove) {
		this.ocenaUstanove=ocenaUstanove;
		this.ocenaProjekcije=ocenaProjekcije;
	}

	public Ustanova getUstanova() {
		return ustanova;
	}

	public void setUstanova(Ustanova u) {
		this.ustanova = u;
	}

	public long getId() {
		return id;
	}

	public void setOcenaProjekcije(double ocenaProjekcije) {
		this.ocenaProjekcije = ocenaProjekcije;
	}

	public void setOcenaUstanove(double ocenaUstanove) {
		this.ocenaUstanove = ocenaUstanove;
	}

	public double getOcenaProjekcije() {
		return ocenaProjekcije;
	}

	public double getOcenaUstanove() {
		return ocenaUstanove;
	}

	public Pogledano getPogledano() {
		return pogledano;
	}

	public void setPogledano(Pogledano pogledano) {
		this.pogledano = pogledano;
	}

	public Posetilac getPosetilac() {
		return posetilac;
	}

	public void setPosetilac(Posetilac posetilac) {
		this.posetilac = posetilac;
	}

}
