package ftn.isa.model.korisnici;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Korisnik implements Serializable {
	
	private static final long serialVersionUID = -578540193667096720L;

	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private long id;
	
	@NotNull
	@Email(message = "Email must be a well-formed address")
	@Column(unique=true,nullable=false)
	private String email;
	
	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(unique=false,nullable=false)
	private String ime;
	

	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(unique=false,nullable=false)
	private String prezime;
	
	@Pattern(regexp="\\w*")
	@NotNull
	@Column(unique=false,nullable=false)
	private String lozinka;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@Column
	private Date datum_rodjenja;
	

	@Column
	private String brojTelefona;
	
	@Column
	private String grad;
	

	@Enumerated(EnumType.STRING)
	private KorisnikTip korisnik_rola;
	
	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public long getId() {
		return id;
	}
	
	public KorisnikTip getUserRole() {
		return korisnik_rola;
	}
	
	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public void setUserRole(KorisnikTip userRole) {
		this.korisnik_rola = userRole;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String name) {
		this.ime = name;
	}

	public void setSurname(String surname) {
		this.prezime = surname;
	}

	public void setPassword(String password) {
		this.lozinka = password;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.datum_rodjenja = dateOfBirth;
	}
	
	public Korisnik(String name, String surname, String email, String password, Date dateOfBirth) {
		this.ime = name;
		this.prezime = surname;
		this.email = email;
		this.lozinka = password;
		this.datum_rodjenja = dateOfBirth;
	}

	public Korisnik() {
	}
	
	public Date getDateOfBirth() {
		return datum_rodjenja;
	}

	
	public String getUserName() {
		return ime;
	}
	
	public String getSurname() {
		return prezime;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return lozinka;
	}
	
	public void setId(long id){
		this.id=id;
	}
}
