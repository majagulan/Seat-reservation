package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Rezervacija;
import ftn.isa.model.korisnici.Posetilac;

public interface RezervacijaRepozitorijum  extends CrudRepository<Rezervacija, Long>{
	
	@Query("select prijatelji from Rezervacija r join r.people prijatelji where r=?1")
	List<Posetilac> getHistoryFriends(Rezervacija r);
	
	@Query("select r from Rezervacija r join r.people p where p=?1")
	List<Rezervacija>getReservationsForGuest(Posetilac g);

}
