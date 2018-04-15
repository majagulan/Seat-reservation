package ftn.isa.entity.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ftn.isa.entity.Institution;

@Entity
@Table(name = "institution_MANAGER")
public class InstitutionManager extends User implements Serializable {

	private static final long serialVersionUID = 9073845010368338002L;

	@ManyToOne
	private Institution institution;

	public InstitutionManager() {

	}

	public Institution getinstitution() {
		return institution;
	}
	
	public void setinstitution(Institution institution) {
		this.institution = institution;
	}
}
