package ftn.isa.entity.users;

import java.io.Serializable;

import javax.persistence.Column;
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
	
	@Column(name = "FIRST_LOGIN", columnDefinition = "boolean default true", insertable = true)
	private boolean firstLogIn;

	public boolean isFirstLogIn() {
		return firstLogIn;
	}

	public void setFirstLogIn(boolean firstLogIn) {
		this.firstLogIn = firstLogIn;
	}

	public InstitutionManager() {

	}

	public Institution getinstitution() {
		return institution;
	}
	
	public void setinstitution(Institution institution) {
		this.institution = institution;
	}
}
