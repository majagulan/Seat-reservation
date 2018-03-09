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
@Table(name = "GRADE")
public class Ocena implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8335307552739844560L;

	@Id
	@Column(name = "GRD_ID")
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Pogledano pogledano;
	
	@ManyToOne
	private Posetilac guest;
	
	@ManyToOne
	private Ustanova bioskop;


	@Column(name = "GRD_PREDST_FILM")
	private double gradeOfOrderItem;

	@Column(name = "GRD_BIO")
	private double gradeOfUstanove;

	public Ocena() {

	}
	
	public Ocena(double gradeOfOrderItem,double gradeOfRestaurant) {
		this.gradeOfOrderItem=gradeOfOrderItem;
		this.gradeOfUstanove=gradeOfRestaurant;
	}

	public Ustanova getUstanova() {
		return bioskop;
	}

	public void setUstanova(Ustanova u) {
		this.bioskop = u;
	}

	public long getId() {
		return id;
	}

	public void setGradeOfOrderItem(double gradeOfOrderItem) {
		this.gradeOfOrderItem = gradeOfOrderItem;
	}

	public void setGradeOfUstanova(double gradeOfUstanova) {
		this.gradeOfUstanove = gradeOfUstanova;
	}

	public double getGradeOfOrderItem() {
		return gradeOfOrderItem;
	}

	public double getGradeOfUstanova() {
		return gradeOfUstanove;
	}

	public Pogledano getPogledano() {
		return pogledano;
	}

	public void setPogledano(Pogledano pogledano) {
		this.pogledano = pogledano;
	}

	public Posetilac getGuest() {
		return guest;
	}

	public void setGuest(Posetilac guest) {
		this.guest = guest;
	}

}
