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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SEGMENT")
public class Segment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624366225591089782L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segment", orphanRemoval = true)
	@JsonIgnore
	private Set<Mesto> mesta = new HashSet<Mesto>();

	@ManyToOne
	private Ustanova ustanova;
	
	@Column(name = "WIDTH")
	private int width;
	
	@Column(name = "HEIGHT")
	private int height;
	
	@Column(name= "SEGMENT_TIP")
	@Enumerated(EnumType.STRING)
	private SegmentTip seg_tip;


	public Segment() {

	}

	
	@JsonProperty
	public void setMesta(Set<Mesto> mesta) {
		this.mesta = mesta;
	}

	public void setUstanova(Ustanova u) {
		this.ustanova = u;
	}


	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Set<Mesto> getMesta() {
		return mesta;
	}

	public Ustanova getUstanova() {
		return ustanova;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setId(Long id){
		this.id=id;
	}

}
