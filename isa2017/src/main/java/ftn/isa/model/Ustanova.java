package ftn.isa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@Entity
public class Ustanova implements Serializable {


	
	
	@Id
	@Column(name = "RES_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "RES_NAME", unique = false, nullable = false)
	private String ustanovaIme;

	@Size(max = 50)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@NotNull
	@Column(name = "RES_DESC", unique = false, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "restaurants", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JsonIgnore
	private Set<Projekcija> repertoar = new HashSet<Projekcija>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Segment> segments = new HashSet<Segment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Ocena> grades = new HashSet<Ocena>();
/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RestaurantManager> restaurantManagers = new HashSet<RestaurantManager>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Worker> workers = new HashSet<Worker>();
	*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Rezervacija> reservations=new HashSet<Rezervacija>();

	@Enumerated(EnumType.STRING)
	private UstanovaTip ustanovaTip;
	
	
	public Ustanova() {

	}
	
	public Ustanova(long id,String ime,String description) {
		this.id=id;
		this.ustanovaIme=ime;
		this.description=description;
	}

	public String getUstanovaIme() {
		return ustanovaIme;
	}

	public void setUstanovaIme(String name) {
		this.ustanovaIme = name;
	}
	@JsonIgnore
	public Set<Ocena> getGrades() {
		return grades;
	}
	@JsonIgnore
	public Set<Projekcija> getRepertoar() {
		return repertoar;
	}
	@JsonIgnore
	public Set<Segment> getSegments() {
		return segments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	/*
	@JsonIgnore
	public Set<RestaurantManager> getRestaurantManagers() {
		return restaurantManagers;
	}
	@JsonProperty
	public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
		this.restaurantManagers = restaurantManagers;
	}
	*/
	@JsonProperty
	public void setMenu(Set<Projekcija> menu) {
		this.repertoar = menu;
	}
	@JsonProperty
	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	@JsonProperty
	public void setGrades(Set<Ocena> grades) {
		this.grades = grades;
	}
	/*
	@JsonIgnore
	public Set<Worker> getWorkers() {
		return workers;
	}
	@JsonProperty
	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
*/
	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Set<Rezervacija> getReservations() {
		return reservations;
	}

	@JsonProperty
	public void setReservations(Set<Rezervacija> reservations) {
		this.reservations = reservations;
	}


	public UstanovaTip getUstanovaTip() {
		return ustanovaTip;
	}

	public void setUstanovaTip(UstanovaTip ustanovaTip) {
		this.ustanovaTip = ustanovaTip;
	}
	
	
	
}
