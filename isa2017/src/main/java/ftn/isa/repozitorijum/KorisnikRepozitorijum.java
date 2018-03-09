package ftn.isa.repozitorijum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.korisnici.Korisnik;

@Repository
public interface KorisnikRepozitorijum  extends CrudRepository<Korisnik,Long>{	
	
public Korisnik findByEmail(String email);
	
	public Korisnik findOne(Long id);
	
	@SuppressWarnings("unchecked")
	public Korisnik save(Korisnik user);
}
