package ftn.isa.model.korisnici;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.model.Ocena;
import ftn.isa.model.Rezervacija;


@Entity
@Table(name="GUEST")
public class Posetilac extends Korisnik {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -546573750075876295L;

	@Enumerated(EnumType.STRING)
	private PosetilacStatus status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.sender", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Prijatelj> sent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.reciever", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Prijatelj> recieved;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "posetilac", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Ocena> ocene;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "people", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Rezervacija> rezervacije;
	
	
	@JsonIgnore
	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}


	@JsonProperty
	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}



	public Posetilac(){
		
	}
	
	

	public PosetilacStatus getStatus() {
		return status;
	}



	public void setStatus(PosetilacStatus status) {
		this.status = status;
	}



	@JsonIgnore
	public Set<Prijatelj> getSent() {
		return sent;
	}

	@JsonProperty
	public void setSent(Set<Prijatelj> sent) {
		this.sent = sent;
	}

	@JsonIgnore
	public Set<Prijatelj> getRecieved() {
		return recieved;
	}

	@JsonProperty
	public void setRecieved(Set<Prijatelj> recieved) {
		this.recieved = recieved;
	}

	@JsonIgnore
	public Set<Ocena> getOcene() {
		return ocene;
	}

	@JsonProperty
	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}
	
	
	

}
