package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import ftn.isa.model.korisnici.Posetilac;

public interface PosetilacRepozitorijum  extends CrudRepository<Posetilac, Long>{
	
	@Query("select s.pk.reciever from Posetilac g join g.sent s where (s.status = 1 and s.pk.sender=?1) ")
	List<Posetilac> getFriendsForSender(Posetilac guest);
	
	Posetilac findByEmail(String email);
	
	@Query("select r.pk.sender from Posetilac g join g.recieved r where (r.status = 1 and r.pk.reciever=?1)")
	List<Posetilac> getFriendsForReciever(Posetilac guest);
	
	@Query("select s.pk.reciever from Posetilac g join g.sent s where (s.status = 0 and s.pk.sender=?1) ")
	List<Posetilac> getSentRequestsForGuest(Posetilac guest);
	
	@Query("select r.pk.sender from Posetilac g join g.recieved r where (r.status = 0 and r.pk.reciever=?1) ")
	List<Posetilac> getRecievedRequestsForGuest(Posetilac guest);
	
	@Query("select s.pk.reciever from Posetilac g join g.sent s where (s.pk.sender=?1) ")
	List<Posetilac> getLinksForSender(Posetilac guest);
	
	@Query("select r.pk.sender from Posetilac g join g.recieved r where (r.pk.reciever=?1)")
	List<Posetilac> getLinksForReciever(Posetilac guest);
	
	@Query("from Posetilac g where g != ?1")
	List<Posetilac> getAllGuests(Posetilac guest);

}
