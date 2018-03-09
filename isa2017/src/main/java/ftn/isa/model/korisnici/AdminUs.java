package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import javax.persistence.Table;


import ftn.isa.model.Ustanova;

@Entity
@Table(name = "USTANOVA_MANAGER")
public class AdminUs extends Korisnik implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1875896751612635821L;
	
	@ManyToOne
	private Ustanova ustanova;

	
	public AdminUs() {

	}

	public Ustanova getUstanova() {
		return ustanova;
	}
	
	public void setUstanova(Ustanova u) {
		this.ustanova = u;
	}


}
