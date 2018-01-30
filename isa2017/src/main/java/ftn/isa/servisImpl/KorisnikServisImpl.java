package ftn.isa.servisImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.korisnici.Korisnik;
import ftn.isa.repozitorijum.KorisnikRepozitorijum;
import ftn.isa.servis.KorisnikServis;

@Service
public class KorisnikServisImpl implements KorisnikServis {

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	@Override
	public Korisnik logIn(String email) {
		return korisnikRepozitorijum.findByEmail(email);
	}

}
