package ftn.isa.model.korisnici;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Korisnik implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String lozinka;
	
	@Column
	private String ime;
	
	@Column
	private String prezime;
	
	@Column
	private String grad;
	
	@Column
	private String brojTelefona;
	
	@Enumerated(EnumType.STRING)
	private KorisnikTip korisnikRola;

	
	public Korisnik(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public KorisnikTip getKorisnikRola() {
		return korisnikRola;
	}

	public void setKorisnikRola(KorisnikTip korisnikRola) {
		this.korisnikRola = korisnikRola;
	}
	
}
