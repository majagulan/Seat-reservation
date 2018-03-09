package ftn.isa.kontroler;

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

import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminSis;
import ftn.isa.model.korisnici.AdminUs;
import ftn.isa.servis.AdminSisServis;

@RestController
@RequestMapping(value = "/systemmanagers")
public class AdminSisKontroler {
	
	@Autowired 
	private AdminSisServis systemManagerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminSis> updateSystem(@RequestBody @Valid AdminSis sm) {
		session.setAttribute("user", sm);
		return systemManagerService.updateAdminSis(sm);
	}
	
	@RequestMapping(value = "/getAllUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Ustanova>> getAllUstanova() {
		return systemManagerService.getAllUstanova();
	}
	
	@RequestMapping(value = "/getRestaurantManagersForUstanova", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<AdminUs>> getAdminUsForUstanova(@RequestParam(value="id") Long id) {
		return systemManagerService.getAdminUsForUstanova(id);
	}
	
	@RequestMapping(value = "/getAllAdminSis", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<AdminSis>> getAllAdminSis() {
		return systemManagerService.getAllAdminSis();
	}
	
	@RequestMapping(value = "/registerAdminUs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminUs> register(@RequestBody @Valid AdminUs sm, @RequestParam(value="id") Long id) {
		return systemManagerService.registerAdminUs(sm, id);
	}
	
	@RequestMapping(value = "/registerSystem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminSis> registerSystem(@RequestBody @Valid AdminSis sm) {
		return systemManagerService.registerAdminSis(sm);
	}
	
	@RequestMapping(value = "/registerUstanova", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ustanova> registerUstanova(@RequestBody @Valid Ustanova r) {
		return systemManagerService.registerUstanova(r);
	}
	
	@RequestMapping(value = "/updateUstanova", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ustanova> updateUstanova(@RequestBody @Valid Ustanova r) {
		return systemManagerService.updateUstanova(r);
	}
	
	@RequestMapping(value = "/deleteUstanova", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Ustanova> deleteUstanova(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeUstanova(r_id);
	}
	@RequestMapping(value = "/deleteAdminSis", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminSis>  deleteAdminSis(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeAdminSis(r_id);
	}
	@RequestMapping(value = "/deleteRestaurantManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<AdminUs> deleteAdminUs(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeAdminUs(r_id);
	}
}
