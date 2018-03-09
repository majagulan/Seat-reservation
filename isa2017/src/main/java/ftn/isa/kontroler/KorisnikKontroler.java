package ftn.isa.kontroler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ftn.isa.model.korisnici.Korisnik;
import ftn.isa.servis.KorisnikServis;


@RestController
@RequestMapping(value="/users")
public class KorisnikKontroler {
	
	@Autowired
	private KorisnikServis userService;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value="/login",
					method=RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> logIn(@RequestBody Korisnik user) {
		Korisnik temp = this.userService.logIn(user);
		if(temp!=null && user.getPassword() != null && temp.getPassword().equals(user.getPassword())){
			session.setAttribute("user", temp);
			return new ResponseEntity<Korisnik>(temp,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/logout",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> logOut() {
		Korisnik temp = (Korisnik) session.getAttribute("user");
		if(temp!=null){
			session.invalidate();
			return new ResponseEntity<Korisnik>(temp,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	
	@RequestMapping(value="/loggedUser",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> getLoggedUser() {
		Korisnik u = (Korisnik)session.getAttribute("user");
		return new ResponseEntity<Korisnik>(u,HttpStatus.OK);
	}

}
