package ftn.isa.kontroler;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.Mesto;
import ftn.isa.model.Pogledano;
import ftn.isa.model.Projekcija;
import ftn.isa.model.Segment;
import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminUs;
import ftn.isa.servis.AdminUsServis;



@RestController
@RequestMapping(value = "/adminus")
public class AdminUsKontroler {
	
	@Autowired
	private AdminUsServis restaurantManagerService;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminUs> update(@RequestBody @Valid AdminUs r) {
		session.setAttribute("user", r);
		return restaurantManagerService.update(r);
	}

	@RequestMapping(value = "/updateUstanova", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ustanova> register(@RequestBody @Valid Ustanova r) {
		return restaurantManagerService.updateUstanovaProfile(r);
	}

	@RequestMapping(value = "/addExistingProjekcija", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projekcija> register(@RequestParam(value = "projekcija_id") Long ids,
			@RequestParam(value = "ust_id") Long ust_id) {
		return restaurantManagerService.addExistingProjekcijaToRepertoar(ids, ust_id);
	}

	@RequestMapping(value = "/removeProjekcija", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projekcija> removeProduct(@RequestParam(value = "projekcija_id") Long ids,
			@RequestParam(value = "ust_id") Long ust_id) {
		return restaurantManagerService.removeProjekcijaFromRepertoar(ids, ust_id);
	}

	@RequestMapping(value = "/addProjekcija", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Projekcija> addProduct(@RequestBody @Valid Projekcija p,
			@RequestParam(value = "ust_id") Long ust_id) {
		return restaurantManagerService.addNewProjekcijaToRepertoar(p, ust_id);
	}

	@RequestMapping(value = "/addMesto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Mesto> addRestaurantTable(@RequestBody @Valid Mesto rt,
			@RequestParam(value = "segment") Long id) {
		return restaurantManagerService.addMestoToSegment(rt, id);
	}

	@RequestMapping(value = "/addSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> addSegment(@RequestBody @Valid Segment s, @RequestParam(value = "rest") Long id) {
		return restaurantManagerService.addSegmentToUstanova(s, id);
	}

	@RequestMapping(value = "/removeSegment", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> removeSegment(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeSegment(id);
	}

	@RequestMapping(value = "/removeMesto", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Mesto> removeMesto(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeMesto(id);
	}

	@RequestMapping(value = "/updateMesto", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Mesto> updateMesto(@RequestBody @Valid Mesto rt,
			@RequestParam(value = "id") Long id) {
		return restaurantManagerService.updateMesto(rt, id);
	}

	@RequestMapping(value = "/updateSegment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> updateSegment(@RequestBody @Valid Segment s) {
		return restaurantManagerService.updateSegment(s);
	}

	@RequestMapping(value = "/getAllSegmentsForUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Segment>> getAllSegmentsForUstanova(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllSegmentsForUstanova(id);
	}

	@RequestMapping(value = "/getAllMestaForSegment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Mesto>> getAllMestaForSegment(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllMestaForSegment(id);
	}

	@RequestMapping(value = "/getAllProjekcijaForUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projekcija>> getAllProductsForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllProjekcijaForUstanova(id);
	}

	@RequestMapping(value = "/getAllProjekcija", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projekcija>> getAllProducts() {
		return restaurantManagerService.getAllProjekcija();
	}

	@RequestMapping(value = "/getGradeForUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getGradeForUstanova(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getGradeForUstanova(id);
	}

	@RequestMapping(value = "/getGradeForPogledano", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getGradeForPogledano(@RequestParam(value = "id") Long id, @RequestParam(value = "res_id") Long res_id) {
		return restaurantManagerService.getGradeForPogledano(id, res_id);
	}

	@RequestMapping(value = "/getUstanovaForManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ustanova> getUstanovaForManager(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getUstanovaForManager(id);
	}

	@RequestMapping(value = "/checkIfSegmentCanBeDeleted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfSegmentCanBeDeleted(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.checkIfSegmentCanBeDeleted(id);
	}

	@RequestMapping(value = "/getAllProjekcijeByNameAndUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Projekcija>> getAllProjekcijeByNameAndUstanova(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name) {
		return restaurantManagerService.getAllProjekcijeByNameAndUstanova(id, name);
	}

	@RequestMapping(value = "/getMesto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Mesto> getMesto(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getMesto(id);
	}

	@RequestMapping(value = "/getReservationsForWeek", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Pogledano>> getReservationsForWeek(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return restaurantManagerService.getReservationsForWeek(id, startDate);
	}
	
	@RequestMapping(value = "/getReservationsForDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Pogledano>> getReservationsForDay(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return restaurantManagerService.getReservationsForDay(id, startDate);
	}

	

}
