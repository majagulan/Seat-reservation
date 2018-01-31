package ftn.isa.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@SuppressWarnings("serial")
@Entity
public class Rezervacija implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Time pocetakRezervacije;
	
	@Column
	private Time krajRezervacije;
	
	@Enumerated(EnumType.STRING)
	private StanjeRez stanjeRez;
	
	public Rezervacija() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getPocetakRezervacije() {
		return pocetakRezervacije;
	}

	public void setPocetakRezervacije(Time pocetakRezervacije) {
		this.pocetakRezervacije = pocetakRezervacije;
	}

	public Time getKrajRezervacije() {
		return krajRezervacije;
	}

	public void setKrajRezervacije(Time krajRezervacije) {
		this.krajRezervacije = krajRezervacije;
	}

	public StanjeRez getStanjeRez() {
		return stanjeRez;
	}

	public void setStanjeRez(StanjeRez stanjeRez) {
		this.stanjeRez = stanjeRez;
	}
	
	
	

}
