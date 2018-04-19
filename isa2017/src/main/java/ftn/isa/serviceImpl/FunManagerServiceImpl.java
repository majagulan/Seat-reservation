package ftn.isa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.Guest;
import ftn.isa.repository.FunManagerRepository;
import ftn.isa.repository.GuestRepository;
import ftn.isa.repository.RequestOfferRepository;
import ftn.isa.repository.RequisiteOfferRepository;
import ftn.isa.service.FunManagerService;

@Service
@Transactional
public class FunManagerServiceImpl implements FunManagerService {

	@Autowired
	private FunManagerRepository bidderRepository;
	
	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private RequisiteOfferRepository bidderOfferRepository;

	@Autowired
	private RequestOfferRepository requestOfferRepository;

	@Override
	public ResponseEntity<FunManager> updateProfile(FunManager b) {
		FunManager temp = this.bidderRepository.findOne(b.getId());
		temp.setDateOfBirth(b.getDateOfBirth());
		temp.setEmail(b.getEmail());
		temp.setUserName(b.getUserName());
		temp.setPassword(b.getPassword());
		temp.setSurname(b.getSurname());
		temp.setFirstLogIn(b.isFirstLogIn());
		return new ResponseEntity<FunManager>(this.bidderRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RequisiteOffer>> getAllBiddingsForThisBidder(Long bidder_id) {
		Guest b = this.guestRepository.findOne(bidder_id);
		return new ResponseEntity<List<RequisiteOffer>>(this.bidderOfferRepository.findByBidder(b), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequisiteOffer> registerBidderOffer(RequisiteOffer bo, Long ro_id, Long b_id) {
		Guest temp = this.guestRepository.findOne(b_id);
		RequestOffer temp1 = this.requestOfferRepository.findOne(ro_id);
		if(bo.getDateOfDelivery().before(temp1.getExpirationDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		bo.setBidder(temp);
		bo.setRequestOffer(temp1);
		return new ResponseEntity<RequisiteOffer>(this.bidderOfferRepository.save(bo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<RequisiteOffer> updateBidderOffer(RequisiteOffer bo) {
		RequisiteOffer temp = this.bidderOfferRepository.findOne(bo.getId());
		temp.setDateOfDelivery(bo.getDateOfDelivery());
		temp.setGaranty(bo.getGaranty());
		temp.setPrice(bo.getPrice());
		return new ResponseEntity<RequisiteOffer>(this.bidderOfferRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequisiteOffer> deleteBidderOffer(Long bidder_id) {
		this.bidderOfferRepository.delete(bidder_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RequestOffer>> getActiveRequestOffers() {
		return new ResponseEntity<List<RequestOffer>>(this.requestOfferRepository.findByStatus(true), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequisiteOffer> getBidderOfferByBidderAndRequestOffer(Long b_id, Long ro_id) {
		return new ResponseEntity<RequisiteOffer>(this.bidderOfferRepository.findByBidderAndRequestOffer(this.bidderRepository.findOne(b_id),  this.requestOfferRepository.findOne(ro_id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> getRequestOffer(Long id) {
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.findOne(id), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<RequisiteOffer> getBidderOffer(Long id) {
		return new ResponseEntity<RequisiteOffer>(this.bidderOfferRepository.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<FunManager>> getAllFunManagers() {
		return new ResponseEntity<Iterable<FunManager>>(this.bidderRepository.findAll(),HttpStatus.OK);
	}

}
