package ftn.isa.entity.users;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="USER_RANK")
public class UserRank implements Serializable {
	
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_RANK_ID")
	@GeneratedValue
	private long id;
	
	@Enumerated(EnumType.STRING)
	private UserRankType userRankType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRank", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Guest> guests;
	
	@JsonIgnore
	public Set<Guest> getGuests() {
		return guests;
	}
	@JsonProperty
	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}

	@Column(name = "USER_RANK_SCALE")
	private Double userRankScale;
	
	public UserRank() {
		
	}

	public UserRank(long id, UserRankType userRankType, Double userRankScale) {
		super();
		this.id = id;
		this.userRankType = userRankType;
		this.userRankScale = userRankScale;
	}

	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	public UserRankType getUserRankType() {
		return userRankType;
	}

	public void setUserRankType(UserRankType userRankType) {
		this.userRankType = userRankType;
	}

	public Double getUserPointsRankScale() {
		return this.userRankScale;
	}

	public void setUserRankScale(Double userRankScale) {
		this.userRankScale = userRankScale;
	}
	
	
}
