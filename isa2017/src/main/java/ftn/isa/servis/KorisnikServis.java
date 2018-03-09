package ftn.isa.servis;

import ftn.isa.model.korisnici.Korisnik;

public interface KorisnikServis {
	
	Korisnik logIn(Korisnik user);
	
	Korisnik updateUser(Korisnik user);
}
