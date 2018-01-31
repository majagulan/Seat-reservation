package ftn.isa.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class TerminProjekcije implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Time terminOdrzavanja;
	
	@Column
	private Date datumOdrzavanja;
	
	public TerminProjekcije() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTerminOdrzavanja() {
		return terminOdrzavanja;
	}

	public void setTerminOdrzavanja(Time terminOdrzavanja) {
		this.terminOdrzavanja = terminOdrzavanja;
	}

	public Date getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Date datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	
}
