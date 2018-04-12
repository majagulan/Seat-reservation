package ftn.isa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.entity.BidderOffer;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.FunManager;

public interface BidderService {
	
	ResponseEntity<FunManager> updateProfile(FunManager b);

	ResponseEntity<List<BidderOffer>> getAllBiddingsForThisBidder(Long bidder_id);

	ResponseEntity<BidderOffer> registerBidderOffer(BidderOffer bo, Long ro_id, Long b_id);

	ResponseEntity<BidderOffer> updateBidderOffer(BidderOffer bo);
	
	ResponseEntity<BidderOffer> getBidderOfferByBidderAndRequestOffer(Long b_id, Long ro_id);

	ResponseEntity<BidderOffer> deleteBidderOffer(Long bidder_id);

	ResponseEntity<List<RequestOffer>> getActiveRequestOffers();

	ResponseEntity<RequestOffer> getRequestOffer(Long id);

	ResponseEntity<BidderOffer> getBidderOffer(Long id);
	
	
}
