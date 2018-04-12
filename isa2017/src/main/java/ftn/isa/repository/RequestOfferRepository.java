package ftn.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.InstitutionManager;

public interface RequestOfferRepository extends CrudRepository<RequestOffer, Long> {
	
	List<RequestOffer> findByStatus(boolean b);
	
	List<RequestOffer> findByInstitutionManager(InstitutionManager m);

}
