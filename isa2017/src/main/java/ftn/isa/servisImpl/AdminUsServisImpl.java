package ftn.isa.servisImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ftn.isa.model.Mesto;
import ftn.isa.model.Pogledano;
import ftn.isa.model.Projekcija;
import ftn.isa.model.Segment;
import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminUs;
import ftn.isa.repozitorijum.AdminUsRepozitorijum;
import ftn.isa.repozitorijum.KorisnikRepozitorijum;
import ftn.isa.repozitorijum.MestoRepozitorijum;
import ftn.isa.repozitorijum.PogledanoRepozitorijum;
import ftn.isa.repozitorijum.ProjekcijaRepozitorijum;
import ftn.isa.repozitorijum.SegmentRepozitorijum;
import ftn.isa.repozitorijum.UstanovaRepozitorijum;
import ftn.isa.servis.AdminUsServis;

@Service
public class AdminUsServisImpl implements AdminUsServis{

	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

	@Autowired
	private KorisnikRepozitorijum userRepository;

	@Autowired
	private UstanovaRepozitorijum restaurantRepository;

	@Autowired
	private ProjekcijaRepozitorijum projekcijaRepository;

	@Autowired
	private SegmentRepozitorijum segmentRepository;

	@Autowired
	private MestoRepozitorijum restaurantTableRepository;

	@Autowired
	private AdminUsRepozitorijum restaurantManagerRepository;

	@Autowired
	private PogledanoRepozitorijum orderRepository;

	@Override
	public ResponseEntity<AdminUs> update(AdminUs sm) {
		AdminUs temp = this.restaurantManagerRepository.findOne(sm.getId());
		if (!temp.getEmail().equals(sm.getEmail()))
			if (this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<AdminUs>(this.restaurantManagerRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ustanova> updateUstanovaProfile(Ustanova r) {
		Ustanova temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setUstanovaIme(r.getUstanovaIme());
		return new ResponseEntity<Ustanova>(this.restaurantRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projekcija> removeProjekcijaFromRepertoar(Long products, Long rest_id) {
		Ustanova t = this.restaurantRepository.findOne(rest_id);
		Projekcija p = this.projekcijaRepository.findOne(products);
		p.getUstanove().remove(t);
		t.getRepertoar().remove(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projekcija> addNewProjekcijaToRepertoar(Projekcija p, Long r_id) {
		Ustanova t = this.restaurantRepository.findOne(r_id);
		t.getRepertoar().add(p);
		p.getUstanove().add(t);
		return new ResponseEntity<Projekcija>(this.projekcijaRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Projekcija> addExistingProjekcijaToRepertoar(Long p_id, Long r_id) {
		Ustanova t = this.restaurantRepository.findOne(r_id);
		Projekcija p = this.projekcijaRepository.findOne(p_id);
		t.getRepertoar().add(p);
		p.getUstanove().add(t);
		return new ResponseEntity<Projekcija>(this.projekcijaRepository.save(p), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> addSegmentToUstanova(Segment s, Long r_id) {
		Ustanova r = this.restaurantRepository.findOne(r_id);
		s.setUstanova(r);
		
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Mesto> addMestoToSegment(Mesto t, Long segment_id) {
		Segment s = this.segmentRepository.findOne(segment_id);
		t.setSegment(s);
		if (this.restaurantTableRepository.findBySegmentAndRedMestaAndKolonaMesta(s, t.getMestoRed(),
				t.getMestoKolona()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Mesto>(this.restaurantTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> removeSegment(Long id) {
		this.segmentRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Mesto> removeMesto(Long id) {
		this.restaurantTableRepository.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Mesto> updateMesto(Mesto t, Long id) {
		t.setSegment(this.segmentRepository.findOne(id));
		if (this.restaurantTableRepository.findBySegmentAndRedMestaAndKolonaMesta(t.getSegment(), t.getMestoRed(),
				t.getMestoKolona()) != null)
			if (this.restaurantTableRepository
					.findBySegmentAndRedMestaAndKolonaMesta(t.getSegment(), t.getMestoRed(), t.getMestoKolona())
					.getId() != t.getId())
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Mesto>(this.restaurantTableRepository.save(t), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Segment> updateSegment(Segment s) {
		Segment temp = this.segmentRepository.findOne(s.getId());
		s.setUstanova(temp.getUstanova());
		
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Segment>> getAllSegmentsForUstanova(Long id) {
		return new ResponseEntity<List<Segment>>(
				this.segmentRepository.findByUstanova(this.restaurantRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Mesto>> getAllMestoForUstanova(Long id) {
		return new ResponseEntity<List<Mesto>>(
				this.restaurantTableRepository.getAllMestoForUstanova(this.restaurantRepository.findOne(id)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Mesto>> getAllMestaForSegment(Long id) {
		return new ResponseEntity<List<Mesto>>((List<Mesto>) this.restaurantTableRepository
				.findBySegment(this.segmentRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Projekcija>> getAllProjekcijaForUstanova(Long id) {
		return new ResponseEntity<List<Projekcija>>(this.projekcijaRepository.getAllProjekcijaForUstanova(id), HttpStatus.OK);
	}
	
	@Override
	public double getGradeForPogledano(Long id, Long res_id) {
		/*if (this.projekcijaRepository.seeIfBelongsToUstanova(id, res_id) == null
				|| this.orderRepository.getGradeForPogledano(id, res_id) == null)
			return -1;
		return this.orderRepository.getGradeForPogledano(id, res_id);*/
		return -1;
	}

	@Override
	public double getGradeForUstanova(Long id) {
		if (this.restaurantRepository.getGradeForUstanova(id) == null)
			return -1;
		return this.restaurantRepository.getGradeForUstanova(id);
	}

	@Override
	public ResponseEntity<Ustanova> getUstanovaForManager(Long id) {
		return new ResponseEntity<Ustanova>(
				this.restaurantRepository.getByManager(this.restaurantManagerRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Projekcija>> getAllProjekcija() {
		return new ResponseEntity<List<Projekcija>>((List<Projekcija>) this.projekcijaRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public double checkIfSegmentCanBeDeleted(Long id) {
		return this.restaurantTableRepository.seeIfCanDeleteSegment(id).size();
	}

	@Override
	public ResponseEntity<List<Projekcija>> getAllProjekcijeByNameAndUstanova(Long id, String name) {
		return new ResponseEntity<List<Projekcija>>(this.projekcijaRepository.findProjekcijaByUstanovaAndName(name, id),
				HttpStatus.OK);
	}
	@Override
	public ResponseEntity<Mesto> getMesto(Long id) {
		return new ResponseEntity<Mesto>(this.restaurantTableRepository.findOne(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Pogledano>> getReservationsForWeek(Long id, String d) throws ParseException {
			Date start = formatter.parse(d);
		    Calendar c = Calendar.getInstance();
		    c.setTime(start);
		    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		    Date weekStart = c.getTime();
		    // we do not need the same day a week after, that's why use 6, not 7
		    c.add(Calendar.DAY_OF_MONTH, 6); 
		    Date weekEnd = c.getTime();
		return new ResponseEntity<List<Pogledano>>(this.orderRepository.getReservationsOfRestaurantForWeek(this.restaurantRepository.findOne(id), weekStart, weekEnd), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Pogledano>> getReservationsForDay(Long id, String startDate) throws ParseException {
		Date start = formatter.parse(startDate);
		return new ResponseEntity<List<Pogledano>>(this.orderRepository.getReservationsOfRestaurantForDay(this.restaurantRepository.findOne(id), start), HttpStatus.OK);

	}


}
