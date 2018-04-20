
package ftn.isa.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.entity.Institution;
import ftn.isa.entity.InstitutionTable;
import ftn.isa.entity.Order;
import ftn.isa.entity.Projection;
import ftn.isa.entity.RequestOffer;
import ftn.isa.entity.RequisiteOffer;
import ftn.isa.entity.RequisiteOfferStatus;
import ftn.isa.entity.Segment;
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.InstitutionManager;
import ftn.isa.repository.FunManagerRepository;
import ftn.isa.repository.InstitutionManagerRepository;
import ftn.isa.repository.InstitutionRepository;
import ftn.isa.repository.InstitutionTableRepository;
import ftn.isa.repository.OrderRepository;
import ftn.isa.repository.ProjectionRepository;
import ftn.isa.repository.RequestOfferRepository;
import ftn.isa.repository.RequisiteOfferRepository;
import ftn.isa.repository.SegmentRepository;
import ftn.isa.service.InstitutionManagerService;
import ftn.isa.service.UserRepository;

@Service
@Transactional
public class InstitutionManagerServiceImpl implements InstitutionManagerService {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private ProjectionRepository projectionRepository;

	@Autowired
	private SegmentRepository segmentRepository;

	@Autowired
	private InstitutionTableRepository institutionTableRepository;


	@Autowired
	private FunManagerRepository bidderRepository;

	@Autowired
	private InstitutionManagerRepository institutionManagerRepository;

	@Autowired
	private RequestOfferRepository requestOfferRepository;

	@Autowired
	private RequisiteOfferRepository bidderOfferRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ResponseEntity<InstitutionManager> update(InstitutionManager sm) {
		InstitutionManager temp = this.institutionManagerRepository.findOne(sm.getId());
		if (!temp.getEmail().equals(sm.getEmail()))
			if (this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<InstitutionManager>(this.institutionManagerRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Institution> updateInstitutionProfile(Institution r) {
		Institution temp = this.institutionRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setinstitutionName(r.getinstitutionName());
		return new ResponseEntity<Institution>(this.institutionRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projection> removeProjectionFromMenu(Long projections, Long rest_id) {
		Institution t = this.institutionRepository.findOne(rest_id);
		Projection p = this.projectionRepository.findOne(projections);
		p.getinstitutions().remove(t);
		t.getMenu().remove(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projection> addNewProjectionToMenu(Projection p, Long r_id) {
		Institution t = this.institutionRepository.findOne(r_id);
		t.getMenu().add(p);
		p.getinstitutions().add(t);
		return new ResponseEntity<Projection>(this.projectionRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projection> addExistingProjectionToMenu(Long p_id, Long r_id) {
		Institution t = this.institutionRepository.findOne(r_id);
		Projection p = this.projectionRepository.findOne(p_id);
		t.getMenu().add(p);
		p.getinstitutions().add(t);
		return new ResponseEntity<Projection>(this.projectionRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id) {
		Institution r = this.institutionRepository.findOne(r_id);
		s.setinstitution(r);
		if (this.segmentRepository.findByPositionAndInstitution(s.getPosition(), r) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InstitutionTable> addInstitutionTableToSegment(InstitutionTable t, Long segment_id) {
		Segment s = this.segmentRepository.findOne(segment_id);
		t.setSegment(s);
		if (this.institutionTableRepository.findBySegmentAndTableRowAndTableColumn(s, t.getTableRow(),
				t.getTableColumn()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<InstitutionTable>(this.institutionTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> removeSegment(Long id) {
		this.segmentRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InstitutionTable> removeInstitutionTable(Long id) {
		if (this.institutionTableRepository.findOne(id).isFree())
			this.institutionTableRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InstitutionTable> updateInstitutionTable(InstitutionTable t, Long id) {
		t.setSegment(this.segmentRepository.findOne(id));
		if (this.institutionTableRepository.findBySegmentAndTableRowAndTableColumn(t.getSegment(), t.getTableRow(),
				t.getTableColumn()) != null)
			if (this.institutionTableRepository
					.findBySegmentAndTableRowAndTableColumn(t.getSegment(), t.getTableRow(), t.getTableColumn())
					.getId() != t.getId())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<InstitutionTable>(this.institutionTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> updateSegment(Segment s) {
		Segment temp = this.segmentRepository.findOne(s.getId());
		s.setinstitution(temp.getinstitution());
		if (this.segmentRepository.findByPositionAndInstitution(s.getPosition(), s.getinstitution()) != null)
			if (this.segmentRepository.findByPositionAndInstitution(s.getPosition(), s.getinstitution()).getId() != s.getId())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}



	@Override
	public ResponseEntity<FunManager> registerBidder(FunManager b) {
		if (this.userRepository.findByEmail(b.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<FunManager>(this.bidderRepository.save(b), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id) {
		String now = formatter.format(new Date());
		String date = formatter.format(ro.getStartDate());
		if (ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (!date.equals(now)) {
			ro.setStatus(false);
			if (ro.getStartDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		FunManager rm = this.bidderRepository.findOne(r_id);
		ro.setFunManager(rm);
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(ro), HttpStatus.OK);
	}



	@Override
	public ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro) {
		String now = formatter.format(new Date());
		String date = formatter.format(ro.getStartDate());
		RequestOffer temp = this.requestOfferRepository.findOne(ro.getId());
		if (ro.getExpirationDate().before(ro.getStartDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (!date.equals(now))
			if (ro.getStartDate().before(new Date()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (now.equals(date)) {
			ro.setStatus(false);
		}
		temp.setStartDate(ro.getStartDate());
		temp.setExpirationDate(ro.getExpirationDate());
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(temp), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<RequestOffer> removeRequestOffer(Long ro) {
		this.requestOfferRepository.delete(ro);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(Long id) {
		return new ResponseEntity<List<RequestOffer>>(
				this.requestOfferRepository.findByFunManager(this.bidderRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RequisiteOffer>> getAllBidderOffersForRequestOffer(Long id) {
		return new ResponseEntity<List<RequisiteOffer>>(
				this.bidderOfferRepository.findByRequestOffer(this.requestOfferRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Segment>> getAllSegmentsForInstitution(Long id) {
		return new ResponseEntity<List<Segment>>(
				this.segmentRepository.findByInstitution(this.institutionRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InstitutionTable>> getAllTablesForInstitution(Long id) {
		return new ResponseEntity<List<InstitutionTable>>(
				this.institutionTableRepository.getTablesForInstitution(this.institutionRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InstitutionTable>> getAllTablesForSegment(Long id) {
		return new ResponseEntity<List<InstitutionTable>>((List<InstitutionTable>) this.institutionTableRepository
				.findBySegment(this.segmentRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Projection>> getAllProjectionsForInstitution(Long id) {
		return new ResponseEntity<List<Projection>>(this.projectionRepository.getProjectionsForInstitution(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> acceptBidderOffer(Long r_id, Long q_id) {
		RequestOffer ro = this.requestOfferRepository.findOne(q_id);
		ro.setStatus(false);
		List<RequisiteOffer> ibo = this.bidderOfferRepository.findByRequestOffer(ro);
		for (int i = 0; i < ibo.size(); i++) {
			if (!ibo.get(i).getId().equals(r_id))
				ibo.get(i).setOfferStatus(RequisiteOfferStatus.DECLINED);
			else
				ibo.get(i).setOfferStatus(RequisiteOfferStatus.ACCEPTED);
		}
		this.bidderOfferRepository.save(ibo);
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.save(ro), HttpStatus.OK);
	}

	@Override
	public double gradeForInstitution(Long id) {
		if (this.institutionRepository.getgradeForInstitution(id) == null)
			return -1;
		return this.institutionRepository.getgradeForInstitution(id);
	}

	@Override
	public double checkIfRequestOfferExpired() {

		List<RequestOffer> of = (List<RequestOffer>) this.requestOfferRepository.findAll();
		for (int i = 0; i < of.size(); i++) {
			String now = formatter.format(new Date());
			String date = formatter.format(of.get(i).getStartDate());
			if (of.get(i).getExpirationDate().before(new Date())) {
				of.get(i).setStatus(false);
				List<RequisiteOffer> it = this.bidderOfferRepository.findByRequestOffer(of.get(i));
				for (int j = 0; j < it.size(); j++) {
					it.get(j).setOfferStatus(RequisiteOfferStatus.DECLINED);
				}
				this.bidderOfferRepository.save(it);
				this.requestOfferRepository.save(of.iterator().next());
			} else if (now.equals(date)) {
				of.get(i).setStatus(true);
				this.requestOfferRepository.save(of.get(i));
			}
		}
		return 0;
	}

	@Override
	public ResponseEntity<Institution> getInstitutionForManager(Long id) {
		return new ResponseEntity<Institution>(
				this.institutionRepository.getByManager(this.institutionManagerRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Projection>> getAllProjections() {
		return new ResponseEntity<List<Projection>>((List<Projection>) this.projectionRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public double checkIfSegmentCanBeDeleted(Long id) {
		return this.institutionTableRepository.seeIfCanDeleteSegment(id).size();
	}


	@Override
	public ResponseEntity<List<Projection>> getAllProjectionsByNameAndInstitution(Long id, String name) {
		return new ResponseEntity<List<Projection>>(this.projectionRepository.findProjectionByInstitutionAndName(name, id),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<InstitutionTable> getInstitutionTable(Long id) {
		return new ResponseEntity<InstitutionTable>(this.institutionTableRepository.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequisiteOffer> getBidderOffer(Long id) {
		return new ResponseEntity<RequisiteOffer>(this.bidderOfferRepository.findOne(id), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Order>> getReservationsForWeek(Long id, String d) throws ParseException {
			Date start = formatter.parse(d);
		    Calendar c = Calendar.getInstance();
		    c.setTime(start);
		    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		    Date weekStart = c.getTime();
		    // we do not need the same day a week after, that's why use 6, not 7
		    c.add(Calendar.DAY_OF_MONTH, 6); 
		    Date weekEnd = c.getTime();
		return new ResponseEntity<List<Order>>(this.orderRepository.getReservationsOfInstitutionForWeek(this.institutionRepository.findOne(id), weekStart, weekEnd), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Order>> getReservationsForDay(Long id, String startDate) throws ParseException {
		Date start = formatter.parse(startDate);
		return new ResponseEntity<List<Order>>(this.orderRepository.getReservationsOfInstitutionForDay(this.institutionRepository.findOne(id), start), HttpStatus.OK);

	}

	@Override
	public double institutionEarnings(Long id, Date startDate, Date endDate) {
		if (this.institutionManagerRepository.getEarningsForInstitution(this.institutionRepository.findOne(id), startDate,
				endDate) == null)
			return -1;
		
		return this.institutionManagerRepository.getEarningsForInstitution(this.institutionRepository.findOne(id), startDate,
				endDate);
	}

	@Override
	public ResponseEntity<Projection> addProjectionToRequestOffer(Long ro_id, Long p_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Projection> removeProjectionFromRequestOffer(Long ro_id, Long p_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFreeTablesCountForInstitution(Long institutionId) {
		Institution i = institutionRepository.findOne(institutionId);
		return institutionTableRepository.getFreeTablesCountForInstitution(i);
	}

	@Override
	public Projection getProjection(Long id) {
		return projectionRepository.findOne(id);
	}

	@Override
	public ResponseEntity<InstitutionManager> updateProfile(InstitutionManager im) {
		InstitutionManager temp = this.institutionManagerRepository.findOne(im.getId());
		temp.setDateOfBirth(im.getDateOfBirth());
		temp.setEmail(im.getEmail());
		temp.setUserName(im.getUserName());
		temp.setPassword(im.getPassword());
		temp.setSurname(im.getSurname());
		temp.setFirstLogIn(im.isFirstLogIn());
		return new ResponseEntity<InstitutionManager>(this.institutionManagerRepository.save(temp),HttpStatus.OK);
		
	}
}
