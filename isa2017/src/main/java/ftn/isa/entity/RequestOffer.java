package ftn.isa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.Guest;

@Entity
@Table(name = "REQUEST_OFFER")
public class RequestOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -715867275692660342L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "STATUS", columnDefinition = "boolean default true", insertable = true)
	private boolean status = true;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@Column(name = "START_DATE", unique = false, nullable = false)
	private Date startDate;
	
	@Column(name = "NAME", unique = false, nullable = true)
	@Pattern(regexp="^[A-Z][a-z_ A-Z0-9]*")
	@Size(max=50)
	private String name;
	
	@Column(name = "DESCRIPTION", unique = false, nullable = true)
	@Pattern(regexp="^[A-Z][a-z_ A-Z0-9]*")
	@Size(max=50)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@NotNull
	@Column(name = "EXPIRATION_DATE", unique = false, nullable = false)
	private Date expirationDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestOffer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RequisiteOffer> bidderOffers = new HashSet<RequisiteOffer>();

	@ManyToOne
	private FunManager funManager;
	
	@ManyToOne
	private Guest guest;

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public RequestOffer() {

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonIgnore
	public Set<RequisiteOffer> getBidderOffers() {
		return bidderOffers;
	}
	@JsonProperty
	public void setBidderOffers(Set<RequisiteOffer> bidderOffers) {
		this.bidderOffers = bidderOffers;
	}

	public Long getId() {
		return id;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setFunManager(FunManager funManager) {
		this.funManager = funManager;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}

	public FunManager getFunManager() {
		return funManager;
	}

}
