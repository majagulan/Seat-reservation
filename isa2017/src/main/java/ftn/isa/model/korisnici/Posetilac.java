package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Posetilac implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String statusClana;
	
	@Column
	private boolean aktivan;
	
	public Posetilac() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusClana() {
		return statusClana;
	}

	public void setStatusClana(String statusClana) {
		this.statusClana = statusClana;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	

}
