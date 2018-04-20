package ftn.isa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.InstitutionManager;

public interface InstitutionManagerService {

	ResponseEntity<Institution> updateInstitutionProfile(Institution r);

	void deleteProjectionFromInstitution(Long projectionId,Long institutionId);
	
	ResponseEntity<Projection> removeProjectionFromMenu(Long projection, Long id);

	ResponseEntity<Projection> addNewProjectionToMenu(Projection p, Long r_id);
	
	ResponseEntity<Projection> addExistingProjectionToMenu(Long p_id, Long r_id);

	ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id);

	ResponseEntity<InstitutionTable> addInstitutionTableToSegment(InstitutionTable t, Long segment_id);
	
	Integer getFreeTablesCountForInstitution(Long institutionId);
	
	Projection getProjection(Long id);

	ResponseEntity<Segment> removeSegment(Long id);

	ResponseEntity<InstitutionTable> removeInstitutionTable(Long t);

	ResponseEntity<InstitutionTable> updateInstitutionTable(InstitutionTable t, Long id);

	ResponseEntity<Segment> updateSegment(Segment s);

	ResponseEntity<FunManager> registerBidder(FunManager b);

	ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id);
	
	ResponseEntity<Projection> addProjectionToRequestOffer(Long ro_id, Long p_id);
	
	ResponseEntity<Projection> removeProjectionFromRequestOffer(Long ro_id, Long p_id);

	ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro);

	ResponseEntity<RequestOffer> removeRequestOffer(Long ro);

	ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(Long id);
	
	ResponseEntity<List<RequestOffer>> getAllRequestOffersForGuest(Long id);

	ResponseEntity<List<RequisiteOffer>> getAllBidderOffersForRequestOffer(Long id);

	ResponseEntity<List<Segment>> getAllSegmentsForInstitution(Long id);

	ResponseEntity<List<InstitutionTable>> getAllTablesForInstitution(Long id);

	ResponseEntity<List<InstitutionTable>> getAllTablesForSegment(Long id);

	ResponseEntity<List<Projection>> getAllProjectionsForInstitution(Long id);

	ResponseEntity<RequestOffer> acceptBidderOffer(Long r_id, Long q_id);
	
	ResponseEntity<List<Projection>> getAllProjectionsByNameAndInstitution(Long id, String name);

	double gradeForInstitution(Long id);

	double institutionEarnings(Long id, Date startDate, Date endDate);


	double checkIfRequestOfferExpired();

	ResponseEntity<Institution> getInstitutionForManager(Long id);

	ResponseEntity<List<Projection>> getAllProjections();

	double checkIfSegmentCanBeDeleted(Long id);

	ResponseEntity<InstitutionManager> update(InstitutionManager r);

	ResponseEntity<InstitutionTable> getInstitutionTable(Long id);

	ResponseEntity<RequisiteOffer> getBidderOffer(Long id);

	ResponseEntity<List<Order>> getReservationsForWeek(Long id, String d) throws ParseException;

	ResponseEntity<List<Order>> getReservationsForDay(Long id, String startDate) throws ParseException;

	ResponseEntity<InstitutionManager> updateProfile(InstitutionManager im);

	


}
