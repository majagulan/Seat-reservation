package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.Guest;

public interface RequestOfferRepository extends CrudRepository<RequestOffer, Long> {
	
	List<RequestOffer> findByStatus(boolean b);
	
	List<RequestOffer> findByGuest(Guest g);
	
	List<RequestOffer> findByFunManager(FunManager m);
	
	@Query("select r from RequestOffer r where r.status= false")
	List<RequestOffer> getAllNonActiveRequestOffers();

}
