package ftn.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.users.FunManager;
import ftn.isa.service.FunManagerService;

@RestController
@RequestMapping(value="/bidders")
public class FunManagerController {
	
	@Autowired
	private FunManagerService bidderService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<FunManager> update(@RequestBody @Valid FunManager b) {
		session.setAttribute("user", b);
		return bidderService.updateProfile(b);
	}
	
	@RequestMapping(value = "/getBiddingsForBidder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequisiteOffer>> getBiddings(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.getAllBiddingsForThisBidder(bidder_id);
	}
	
	@RequestMapping(value = "/getAllFunManagers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<FunManager>> getAllFunManagers() {
		return bidderService.getAllFunManagers();
	}
	
	@RequestMapping(value = "/getRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> getRequestOffer(@RequestParam(value = "id") Long id) {
		return bidderService.getRequestOffer(id);
	}
	
	@RequestMapping(value = "/getActiveBiddingsForBidder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequisiteOffer>> getActiveBiddings(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.getAllBiddingsForThisBidder(bidder_id);
	}
	
	@RequestMapping(value = "/registerBid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> registerBid(@RequestBody @Valid RequisiteOffer bo, @RequestParam(value="request_offer_id") Long ro_id, @RequestParam(value="bidder_id") Long b_id) {
		return bidderService.registerBidderOffer(bo, ro_id, b_id);
	}
	
	@RequestMapping(value = "/updateBid", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> UpdateBid(@RequestBody @Valid RequisiteOffer bo) {
		return bidderService.updateBidderOffer(bo);
	}
	
	@RequestMapping(value = "/deleteBid", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> DeleteBid(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.deleteBidderOffer(bidder_id);
	}
	
	@RequestMapping(value = "/getActiveRequestOffers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequestOffer>> getActiveRequestOffers() {
		return bidderService.getActiveRequestOffers();
	}
	
	@RequestMapping(value = "/getBidderOfferForBidderAndRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> getBidderOfferForBidderAndRequestOffer(@RequestParam(value = "b_id") Long bidder_id, @RequestParam(value = "ro_id") Long request_offer_id) {
		return bidderService.getBidderOfferByBidderAndRequestOffer(bidder_id, request_offer_id);
	}
	
	@RequestMapping(value = "/getBidderOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> getBidderOffer(@RequestParam(value = "id") Long id) {
		return bidderService.getBidderOffer(id);
	}
	
}
