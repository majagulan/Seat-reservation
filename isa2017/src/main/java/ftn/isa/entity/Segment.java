package ftn.isa.entity;

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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SEGMENT", uniqueConstraints = {
@UniqueConstraint(columnNames = { "institution_RES_ID", "SGM_POS"}) })
public class Segment implements Serializable {

	private static final long serialVersionUID = 5329505235746769431L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segment", orphanRemoval = true)
	@JsonIgnore
	private Set<InstitutionTable> tables = new HashSet<InstitutionTable>();

	@ManyToOne
	private Institution institution;
	
	@Column(name = "WIDTH")
	private int width;
	
	@Column(name = "HEIGHT")
	private int height;

	@Enumerated(EnumType.STRING)
	@Column(name = "SGM_POS")
	private SegmentType position;

	public Segment() {

	}

	@JsonProperty
	public void setTables(Set<InstitutionTable> tables) {
		this.tables = tables;
	}

	public void setinstitution(Institution institution) {
		this.institution = institution;
	}
	
	public Long getId() {
		return id;
	}

	public SegmentType getPosition() {
		return position;
	}

	public void setPosition(SegmentType position) {
		this.position = position;
	}

	@JsonIgnore
	public Set<InstitutionTable> getTables() {
		return tables;
	}

	public Institution getinstitution() {
		return institution;
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
