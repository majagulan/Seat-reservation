package ftn.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Veza implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private int statusVeze;
	
	public Veza() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatusVeze() {
		return statusVeze;
	}

	public void setStatusVeze(int statusVeze) {
		this.statusVeze = statusVeze;
	}
	
	

}
