package ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "RED", "KOLONA", "SEGMENT_ID" }) })
public class Mesto {
	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Segment segment;


	@Min(1)
	@Max(20)
	@NotNull
	@Column
	private int brojMesta;


	@Column(name = "RED")
	private int redMesta;

	@Column(name = "KOLONA")
	private int kolonaMesta;
	
	@Column
	private boolean free;

	public boolean isFree() {
		return free;
	}


	public void setFree(boolean free) {
		this.free = free;
	}


	public Mesto() {
		this.id = null;
	}


	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Long getId() {
		return id;
	}

	public Segment getSegment() {
		return segment;
	}

	
	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public int getMestoRed() {
		return redMesta;
	}

	public void setMestoRed(int red) {
		this.redMesta = red;
	}

	public int getMestoKolona() {
		return kolonaMesta;
	}

	public void setMestoKolona(int kolonaMesta) {
		this.kolonaMesta = kolonaMesta;
	}

}
