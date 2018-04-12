package ftn.isa.entity.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.entity.Institution;
import ftn.isa.entity.RequestOffer;

@Entity
@Table(name = "institution_MANAGER")
public class InstitutionManager extends User implements Serializable {

	private static final long serialVersionUID = 9073845010368338002L;

	@ManyToOne
	private Institution institution;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institutionManager", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RequestOffer> requestOffers = new HashSet<RequestOffer>();

	public InstitutionManager() {

	}

	public Institution getinstitution() {
		return institution;
	}
	
	public void setinstitution(Institution institution) {
		this.institution = institution;
	}
	@JsonIgnore
	public Set<RequestOffer> getRequestOffers() {
		return requestOffers;
	}
	@JsonProperty
	public void setRequestOffers(Set<RequestOffer> requestOffers) {
		this.requestOffers = requestOffers;
	}
}
