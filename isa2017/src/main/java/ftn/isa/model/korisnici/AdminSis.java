package ftn.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="SYSTEM_MANAGER")
public class AdminSis extends Korisnik {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388959791433064968L;
	
	@Column(name = "PREDEFINED", columnDefinition = "boolean default false", insertable = false)
	private boolean predefined;
	
	public AdminSis() {
		super();
	}

	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
	}
	

}
