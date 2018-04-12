package ftn.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.BidderOffer;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.FunManager;

public interface BidderOfferRepository extends CrudRepository<BidderOffer, Long> {
	
	List<BidderOffer> findByBidder(FunManager b);

	BidderOffer findByBidderAndRequestOffer(FunManager b, RequestOffer m);

	List<BidderOffer> findByRequestOffer(RequestOffer ro);
}
