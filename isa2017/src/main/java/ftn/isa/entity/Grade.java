package ftn.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ftn.isa.entity.users.Guest;

@Entity
@Table(name = "GRADE")
public class Grade implements Serializable {

	private static final long serialVersionUID = -5958329526192915938L;

	@Id
	@Column(name = "GRD_ID")
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Projection projection;
	
	@ManyToOne
	private Guest guest;
	
	@ManyToOne
	private Institution institution;
	
	@ManyToOne
	private Reservation reservation;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Column(name = "GRD_MEAL")
	private double gradeOfOrderItem;

	@Column(name = "GRD_RES")
	private double gradeOfInstitution;

	public Grade() {

	}
	
	public Grade(double gradeOfOrderItem,double gradeOfInstitution) {
		this.gradeOfOrderItem=gradeOfOrderItem;
		this.gradeOfInstitution=gradeOfInstitution;
	}

	public Institution getinstitution() {
		return institution;
	}

	public void setinstitution(Institution institution) {
		this.institution = institution;
	}

	public long getId() {
		return id;
	}
	
	public void setGradeOfOrderItem(double gradeOfOrderItem) {
		this.gradeOfOrderItem = gradeOfOrderItem;
	}

	public void setgradeOfInstitution(double gradeOfInstitution) {
		this.gradeOfInstitution = gradeOfInstitution;
	}

	public double getGradeOfOrderItem() {
		return gradeOfOrderItem;
	}

	public double getgradeOfInstitution() {
		return gradeOfInstitution;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
}
