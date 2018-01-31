package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class AdminSis implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public AdminSis() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
