package ftn.isa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "PROJEKCIJA")
public class Projekcija implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1588651114694518560L;

	
	@Id
	@Column(name = "PR_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(nullable = false)
	private String imeProjekcije;

	@Column
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	private String description;

	@NotNull
	@Digits(integer=6, fraction=2)
	@Column(nullable = false)
	private Double price;

	@Enumerated(EnumType.STRING)
	private ProjekcijaTip projekcijaTip;

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JoinTable(name = "USTANOVE_PROJEKCIJE", joinColumns = @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID"), inverseJoinColumns = @JoinColumn(name = "UST_ID", referencedColumnName = "UST_ID"))
	@JsonIgnore
	private Set<Ustanova> ustanove = new HashSet<Ustanova>();
	
	@Column
	private String imeReditelja;
	
	@Column
	private int trajanje;
	
	@Column
	private String poster;
	
	@Column
	private double prosecnaOcena;
	
	@Column
	private int popust;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Zanr zanr;

	
	public Projekcija() {

	}

	public long getId() {
		return id;
	}

	public ProjekcijaTip getProjekcijaTip() {
		return projekcijaTip;
	}

	public String getImeProjekcije() {
		return imeProjekcije;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
	@JsonIgnore
	public Set<Ustanova> getUstanova() {
		return ustanove;
	}
	@JsonProperty
	public void setUstanova(Set<Ustanova> ustanove) {
		this.ustanove = ustanove;
	}

	public void setImeProjekcije(String imeProjekcije) {
		this.imeProjekcije = imeProjekcije;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setTipProjekcije(ProjekcijaTip tipProjekcije) {
		this.projekcijaTip = tipProjekcije;
	}
	public Set<Ustanova> getUstanove() {
		return ustanove;
	}
	public void setUstanove(Set<Ustanova> ustanove) {
		this.ustanove = ustanove;
	}
	public String getImeReditelja() {
		return imeReditelja;
	}
	public void setImeReditelja(String imeReditelja) {
		this.imeReditelja = imeReditelja;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	public int getPopust() {
		return popust;
	}
	public void setPopust(int popust) {
		this.popust = popust;
	}
	public Zanr getZanr() {
		return zanr;
	}
	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setProjekcijaTip(ProjekcijaTip projekcijaTip) {
		this.projekcijaTip = projekcijaTip;
	} 
	
	

}
