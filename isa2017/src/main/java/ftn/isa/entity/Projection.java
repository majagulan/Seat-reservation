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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PROJECTION")
public class Projection implements Serializable {

	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@Column(name = "PR_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "PR_NAME", nullable = false)
	private String projectionName;
	
	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "PR_DIRECTOR", nullable = false)
	private String director;

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	@Column(name = "PR_DES")
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	private String description;
	
	@Column(name = "PR_ACTOR")
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	private String actor;

	@Column(name = "PR_DURATION")
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	private String duration;

	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

	@NotNull
	@Digits(integer=6, fraction=2)
	@Column(name = "PR_PRICE", nullable = false)
	private Double price;

	@Enumerated(EnumType.STRING)
	private ProjectionType projectionType;

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JoinTable(name = "institution_PROJECTIONS", joinColumns = @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID"), inverseJoinColumns = @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID"))
	@JsonIgnore
	private Set<Institution> institutions = new HashSet<Institution>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Order> orders;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Grade> grades = new HashSet<Grade>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<ProjectionTime> projectionTimes = new HashSet<ProjectionTime>();
	
	@JsonIgnore
	public Set<ProjectionTime> getProjectionTimes() {
		return projectionTimes;
	}
	@JsonProperty
	public void setProjectionTimes(Set<ProjectionTime> projectionTimes) {
		this.projectionTimes = projectionTimes;
	}
	@JsonIgnore
	public Set<Grade> getGrades() {
		return grades;
	}
	@JsonProperty
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}
	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}
	@JsonProperty
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public Projection() {

	}

	public long getId() {
		return id;
	}

	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public ProjectionType getProjectionType() {
		return projectionType;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
	public Projection(long id, String projectionName, String description, String actor, String duration, Double price,
			ProjectionType projectionType, Set<Institution> institutions) {
		super();
		this.id = id;
		this.projectionName = projectionName;
		this.description = description;
		this.actor = actor;
		this.duration = duration;
		this.price = price;
		this.projectionType = projectionType;
		this.institutions = institutions;
	}
	@JsonIgnore
	public Set<Institution> getinstitutions() {
		return institutions;
	}
	@JsonProperty
	public void setinstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}


	public void setProjectionName(String name) {
		this.projectionName = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProjectionType(ProjectionType projectionType) {
		this.projectionType = projectionType;
	}

}
