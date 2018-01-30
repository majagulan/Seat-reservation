package ftn.isa.kontroler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.korisnici.Korisnik;
import ftn.isa.servis.KorisnikServis;

@RestController
@RequestMapping("/korisnici")
public class KorisnikKontroler {
	
	@Autowired
	private KorisnikServis korisnikServis;
	
	@RequestMapping(value ="/login",
					method = RequestMethod.POST)
	public ResponseEntity<Korisnik> logIn(@RequestBody Korisnik korisnik){
		Korisnik temp = korisnikServis.logIn(korisnik.getEmail());
		if(temp!= null && korisnik.getLozinka() != null && temp.getLozinka().equals(korisnik.getLozinka())){
			return new ResponseEntity<Korisnik>(temp,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

}
