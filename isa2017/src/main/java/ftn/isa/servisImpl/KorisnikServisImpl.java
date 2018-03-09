package ftn.isa.servisImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.korisnici.Korisnik;
import ftn.isa.repozitorijum.KorisnikRepozitorijum;
import ftn.isa.servis.KorisnikServis;

@Service
public class KorisnikServisImpl implements KorisnikServis {

	@Autowired
	private KorisnikRepozitorijum userRepository;
	
	@Override
	public Korisnik logIn(Korisnik user) {
		String email = user.getEmail();
		return this.userRepository.findByEmail(email);
	}


	@Override
	public Korisnik updateUser(Korisnik user) {
		return userRepository.save(user);
	}

}
