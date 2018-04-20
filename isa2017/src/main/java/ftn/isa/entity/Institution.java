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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.entity.users.InstitutionManager;

@Entity
@Table(name = "institution")
public class Institution implements Serializable {

	private static final long serialVersionUID = -2730772573761285789L;

	@Id
	@Column(name = "RES_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "RES_NAME", unique = false, nullable = false)
	private String institutionName;

	@Size(max = 50)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@NotNull
	@Column(name = "RES_DESC", unique = false, nullable = false)
	private String description;
	
	@Column(name = "INS_TYPE")
	@Enumerated(EnumType.STRING)
	private InstitutionType institutionType;
	
	@Column(name="ADR_INS")
	private String adresaIns;
	
	@Column(name="GMAPSURL")
	@Length(max=2084)
	private String gmapsUrl;


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "institutions", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JsonIgnore
	private Set<Projection> menu = new HashSet<Projection>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Segment> segments = new HashSet<Segment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Grade> grades = new HashSet<Grade>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<InstitutionManager> institutionManagers = new HashSet<InstitutionManager>();

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Reservation> reservations=new HashSet<Reservation>();

	public Institution() {

	}
	
	public Institution(long id,String institutionName,String description, String adresa, String gmapsUrl) {
		this.id=id;
		this.institutionName=institutionName;
		this.description=description;
		this.adresaIns=adresa;
		this.gmapsUrl=gmapsUrl;
	}

	public String getinstitutionName() {
		return institutionName;
	}

	public void setinstitutionName(String name) {
		this.institutionName = name;
	}
	@JsonIgnore
	public Set<Grade> getGrades() {
		return grades;
	}
	@JsonIgnore
	public Set<Projection> getMenu() {
		return menu;
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
	@JsonIgnore
	public Set<InstitutionManager> getinstitutionManagers() {
		return institutionManagers;
	}
	@JsonProperty
	public void setinstitutionManagers(Set<InstitutionManager> institutionManagers) {
		this.institutionManagers = institutionManagers;
	}
	@JsonProperty
	public void setMenu(Set<Projection> menu) {
		this.menu = menu;
	}
	@JsonProperty
	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	@JsonProperty
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Set<Reservation> getReservations() {
		return reservations;
	}

	@JsonProperty
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public InstitutionType getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(InstitutionType institutionType) {
		this.institutionType = institutionType;
	}

	public String getAdresaIns() {
		return adresaIns;
	}

	public void setAdresaIns(String adresaIns) {
		this.adresaIns = adresaIns;
	}
	
	public String getGmapsUrl() {
		return gmapsUrl;
	}

	public void setGmapsUrl(String gmapsUrl) {
		this.gmapsUrl = gmapsUrl;
	}


}
