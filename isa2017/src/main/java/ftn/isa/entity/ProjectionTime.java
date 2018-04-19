package ftn.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectionTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@DecimalMin("00.00")
	@DecimalMax("24.00")
	@Digits(integer = 2, fraction = 2)
	@NotNull
	@Column(name = "START_TIME")
	private double startTime;
	
	@ManyToOne
	private Projection projection;
	
	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public ProjectionTime() {
		
	}

	public ProjectionTime(Long id, double startTime) {
		super();
		this.id = id;
		this.startTime = startTime;
	}


	public Long getId() {
		return id;
	}


	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	


}
