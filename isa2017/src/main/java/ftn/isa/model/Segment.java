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
public class Segment implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String naziv;
	
	@Enumerated(EnumType.STRING)
	private SegmentTip segmentTip;
	
	public Segment() {
		
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

	public SegmentTip getSegmentTip() {
		return segmentTip;
	}

	public void setSegmentTip(SegmentTip segmentTip) {
		this.segmentTip = segmentTip;
	}
	
	
	
	

}
