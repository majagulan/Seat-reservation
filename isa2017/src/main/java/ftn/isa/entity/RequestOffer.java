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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.entity.users.FunManager;

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
