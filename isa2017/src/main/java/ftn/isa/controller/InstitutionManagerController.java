package ftn.isa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.InstitutionManager;
import ftn.isa.mail.SendEmail;
import ftn.isa.service.InstitutionManagerService;

@RestController
@RequestMapping(value = "/institutionmanagers")
public class InstitutionManagerController {

	@Autowired
	private InstitutionManagerService institutionManagerService;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionManager> update(@RequestBody @Valid InstitutionManager r) {
		session.setAttribute("user", r);
		return institutionManagerService.update(r);
	}
	
	@RequestMapping(value = "/deleteProjectionFromInstitution/{projectionId}/{institutionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public void deleteProjectionFromInstitution(@PathVariable("projectionId") Long projectionId,@PathVariable("institutionId") Long institutionId) {
		 institutionManagerService.deleteProjectionFromInstitution(projectionId,institutionId);
	}

	@RequestMapping(value = "/updateinstitution", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Institution> register(@RequestBody @Valid Institution r) {
		return institutionManagerService.updateInstitutionProfile(r);
	}

	@RequestMapping(value = "/addExistingProjection", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> register(@RequestParam(value = "projection_id") Long ids,
			@RequestParam(value = "rest_id") Long rest_id) {
		return institutionManagerService.addExistingProjectionToMenu(ids, rest_id);
	}

	@RequestMapping(value = "/removeProjection", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> removeProjection(@RequestParam(value = "projection_id") Long ids,
			@RequestParam(value = "rest_id") Long rest_id) {
		return institutionManagerService.removeProjectionFromMenu(ids, rest_id);
	}

	@RequestMapping(value = "/addProjection", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> addProjection(@RequestBody @Valid Projection p,
			@RequestParam(value = "rest_id") Long rest_id) {
		return institutionManagerService.addNewProjectionToMenu(p, rest_id);
	}

	@RequestMapping(value = "/addinstitutionTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionTable> addinstitutionTable(@RequestBody @Valid InstitutionTable rt,
			@RequestParam(value = "segment") Long id) {
		return institutionManagerService.addInstitutionTableToSegment(rt, id);
	}

	@RequestMapping(value = "/addSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> addSegment(@RequestBody @Valid Segment s, @RequestParam(value = "rest") Long id) {
		return institutionManagerService.addSegmentToRestaurnat(s, id);
	}

	@RequestMapping(value = "/removeSegment", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> removeSegment(@RequestParam(value = "id") Long id) {
		return institutionManagerService.removeSegment(id);
	}

	@RequestMapping(value = "/removeInstitutionTable", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionTable> removeInstitutionTable(@RequestParam(value = "id") Long id) {
		return institutionManagerService.removeInstitutionTable(id);
	}

	@RequestMapping(value = "/updateInstitutionTable", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionTable> updateInstitutionTable(@RequestBody @Valid InstitutionTable rt,
			@RequestParam(value = "id") Long id) {
		return institutionManagerService.updateInstitutionTable(rt, id);
	}

	@RequestMapping(value = "/updateSegment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> updateSegment(@RequestBody @Valid Segment s) {
		return institutionManagerService.updateSegment(s);
	}


	@RequestMapping(value = "/updateRequestOffer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> updateRequestOffer(@RequestBody @Valid RequestOffer ro) {
		return institutionManagerService.updateRequestOffer(ro);
	}

	@RequestMapping(value = "/removeProjectionFromRequestOffer", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> removeProjectionFromRequestOffer(@RequestParam(value = "projection_id") Long ids,
			@RequestParam(value = "ro_id") Long rest_id) {
		return institutionManagerService.removeProjectionFromRequestOffer(ids, rest_id);
	}

	@RequestMapping(value = "/addProjectionToRequestOffer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projection> addProjectionToRequestOffer(@RequestParam(value = "projection_id") Long id,
			@RequestParam(value = "rest_id") Long rest_id) {
		return institutionManagerService.addProjectionToRequestOffer(id, rest_id);
	}

	@RequestMapping(value = "/removeRequestOffer", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> removeRequestOffer(@RequestParam(value = "id") Long id) {
		return institutionManagerService.removeRequestOffer(id);
	}

	@RequestMapping(value = "/registerBidder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<FunManager> registerBidder(@RequestBody @Valid FunManager s) {
		return institutionManagerService.registerBidder(s);
	}


	@RequestMapping(value = "/getAllRequestOffersForManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllRequestOffersForManager(id);
	}
	
	@RequestMapping(value = "/getAllRequestOffersForGuest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequestOffer>> getAllRequestOffersForGuest(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllRequestOffersForGuest(id);
	}

	@RequestMapping(value = "/getAllBidderOffersForRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequisiteOffer>> getAllBidderOffersForRequestOffer(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllBidderOffersForRequestOffer(id);
	}

	@RequestMapping(value = "/getAllSegmentsForInstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Segment>> getAllSegmentsForInstitution(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllSegmentsForInstitution(id);
	}

	@RequestMapping(value = "/getAllTablesForSegment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<InstitutionTable>> getAllTablesForSegment(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllTablesForSegment(id);
	}

	@RequestMapping(value = "/getAllProjectionsForInstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projection>> getAllProjectionsForInstitution(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllProjectionsForInstitution(id);
	}

	@RequestMapping(value = "/registerRequestOffer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> registerRequestOffer(@RequestBody RequestOffer ro,@RequestParam(value = "rm_id") Long id) {
		return institutionManagerService.registerRequestOffer(ro,id);
	}
	
	@RequestMapping(value = "/getAllProjections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projection>> getAllProjections() {
		return institutionManagerService.getAllProjections();
	}

	/*@RequestMapping(value = "/getAllProjectionsForRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projection>> getAllProjectionsForRequestOffer(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getAllProjectionsForRequestOffer(id);
	}*/

	@RequestMapping(value = "/acceptBidderOffer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> acceptBidderOffer(@RequestParam(value = "bid_id") Long r_id,
			@RequestParam(value = "req_id") Long q_id) {
		List<RequisiteOffer> biddings = this.institutionManagerService.getAllBidderOffersForRequestOffer(q_id).getBody();
		for (int i = 0; i < biddings.size(); i++) {
			if (biddings.get(i).getId() == r_id) {
				new SendEmail(biddings.get(i).getBidder().getEmail(),
						"<a href=http://localhost:8000/#!/home> Check </a>", "Notification",
						"Your bidder offer " + biddings.get(i).getId() + " is accepted. Check here :").start();
			} else {
				new SendEmail(biddings.get(i).getBidder().getEmail(),
						"<a href=http://localhost:8000/#!/home> Check </a>", "Notification",
						"Your bidder offer " + biddings.get(i).getId() + " is declined. Check here :").start();
			}
		}
		return institutionManagerService.acceptBidderOffer(r_id, q_id);
	}

	@RequestMapping(value = "/getgradeForInstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getgradeForInstitution(@RequestParam(value = "id") Long id) {
		return institutionManagerService.gradeForInstitution(id);
	}

	@RequestMapping(value = "/getinstitutionEarnings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getinstitutionEarnings(@RequestParam(value = "id") Long id,
			@RequestParam(value = "start") String startDate, @RequestParam(value = "end") String endDate)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date start = formatter.parse(startDate);
		Date end = formatter.parse(endDate);
		return institutionManagerService.institutionEarnings(id, start, end);
	}


	@RequestMapping(value = "/getInstitutionForManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Institution> getInstitutionForManager(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getInstitutionForManager(id);
	}

	@RequestMapping(value = "/checkIfRequestOfferExpired", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfRequestOfferExpired() {
		return institutionManagerService.checkIfRequestOfferExpired();
	}

	@RequestMapping(value = "/checkIfSegmentCanBeDeleted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfSegmentCanBeDeleted(@RequestParam(value = "id") Long id) {
		return institutionManagerService.checkIfSegmentCanBeDeleted(id);
	}

	@RequestMapping(value = "/getAllProjectionsByNameAndInstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projection>> getAllProjectionsByNameAndInstitution(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name) {
		return institutionManagerService.getAllProjectionsByNameAndInstitution(id, name);
	}

	@RequestMapping(value = "/getInstitutionTable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionTable> getInstitutionTable(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getInstitutionTable(id);
	}

	@RequestMapping(value = "/getBidderOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequisiteOffer> getBidderOffer(@RequestParam(value = "id") Long id) {
		return institutionManagerService.getBidderOffer(id);
	}
	
	@RequestMapping(value = "/getReservationsForWeek", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Order>> getReservationsForWeek(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return institutionManagerService.getReservationsForWeek(id, startDate);
	}
	
	@RequestMapping(value = "/getReservationsForDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Order>> getReservationsForDay(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return institutionManagerService.getReservationsForDay(id, startDate);
	}
}
