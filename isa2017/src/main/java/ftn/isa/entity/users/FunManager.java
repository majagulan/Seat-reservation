package ftn.isa.entity.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ftn.isa.entity.RequestOffer;

@Entity
@Table(name = "BIDDER")
public class FunManager extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7346796776981937378L;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funManager", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RequestOffer> requestOffers = new HashSet<RequestOffer>();

	@JsonIgnore
	public Set<RequestOffer> getRequestOffers() {
		return requestOffers;
	}
	@JsonProperty
	public void setRequestOffers(Set<RequestOffer> requestOffers) {
		this.requestOffers = requestOffers;
	}

	@Column(name = "FIRST_LOGIN", columnDefinition = "boolean default true", insertable = true)
	private boolean firstLogIn;

	public FunManager() {
	}

	public void setFirstLogIn(boolean firstLogIn) {
		this.firstLogIn = firstLogIn;
	}

	public boolean isFirstLogIn() {
		return firstLogIn;
	}

}
