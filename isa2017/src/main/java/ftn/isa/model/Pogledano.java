package ftn.isa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "UST_POGL")
public class Pogledano implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4972636127613770044L;
	
	@Id
	@Column(name = "UST_POGL_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "RES_ORDUST_POGL_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "POGL_TIME")
	private double time;
	
	@ManyToOne(optional = false)
	private Mesto mesto;

	@ManyToOne
	private Rezervacija rezervacija;
	

	@Column(name = "PRICE")
	private double price;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="pogledano",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Ocena> grades;

	public Pogledano() {
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMesto(Mesto m) {
		this.mesto = m;
	}

	public void setId(Long id){
		this.id=id;
	}
	
	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija r) {
		this.rezervacija = r;
	}

	public Date getDate() {
		return date;
	}

	public Long getId() {
		return id;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@JsonIgnore
	public Set<Ocena> getGrades() {
		return grades;
	}

	@JsonProperty
	public void setGrades(Set<Ocena> grades) {
		this.grades = grades;
	}

}
