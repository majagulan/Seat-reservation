package ftn.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.FunManager;

public interface RequisiteOfferRepository extends CrudRepository<RequisiteOffer, Long> {
	
	List<RequisiteOffer> findByBidder(FunManager b);

	RequisiteOffer findByBidderAndRequestOffer(FunManager b, RequestOffer m);

	List<RequisiteOffer> findByRequestOffer(RequestOffer ro);
}
