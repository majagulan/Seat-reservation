package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class AdminFan implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public AdminFan() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
