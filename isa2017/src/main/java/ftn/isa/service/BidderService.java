package ftn.isa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.users.FunManager;

public interface BidderService {
	
	ResponseEntity<FunManager> updateProfile(FunManager b);

	ResponseEntity<List<RequisiteOffer>> getAllBiddingsForThisBidder(Long bidder_id);

	ResponseEntity<RequisiteOffer> registerBidderOffer(RequisiteOffer bo, Long ro_id, Long b_id);

	ResponseEntity<RequisiteOffer> updateBidderOffer(RequisiteOffer bo);
	
	ResponseEntity<RequisiteOffer> getBidderOfferByBidderAndRequestOffer(Long b_id, Long ro_id);

	ResponseEntity<RequisiteOffer> deleteBidderOffer(Long bidder_id);

	ResponseEntity<List<RequestOffer>> getActiveRequestOffers();

	ResponseEntity<RequestOffer> getRequestOffer(Long id);

	ResponseEntity<RequisiteOffer> getBidderOffer(Long id);
	
	
}
