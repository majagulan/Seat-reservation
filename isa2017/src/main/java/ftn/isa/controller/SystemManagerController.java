package ftn.isa.controller;

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
import ftn.isa.entity.users.FunManager;
import ftn.isa.entity.users.InstitutionManager;
import ftn.isa.entity.users.SystemManager;
import ftn.isa.entity.users.UserRank;
import ftn.isa.service.SystemManagerService;

@RestController
@RequestMapping(value = "/systemmanagers")
public class SystemManagerController {

	@Autowired 
	private SystemManagerService systemManagerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager> updateSystem(@RequestBody @Valid SystemManager sm) {
		session.setAttribute("user", sm);
		return systemManagerService.updateSystemManager(sm);
	}
	
	@RequestMapping(value = "/getAllinstitutions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Institution>> getAllinstitutions() {
		return systemManagerService.getAllinstitutions();
	}
	
	@RequestMapping(value = "/getAllUserRanks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<UserRank>> getAllUserRanks() {
		return systemManagerService.getAllUserRanks();
	}
	
	@RequestMapping(value = "/changeScale/{userRankId}/{newScale}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<UserRank> getAllUserRanks(@PathVariable("userRankId") Long userRankId, @PathVariable("newScale") Double newScale) {
		return systemManagerService.changeScale(userRankId, newScale);
	}
	
	@RequestMapping(value = "/getinstitutionManagersForinstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<InstitutionManager>> getinstitutionManagersForinstitution(@RequestParam(value="id") Long id) {
		return systemManagerService.getinstitutionManagersForinstitution(id);
	}
	
	@RequestMapping(value = "/getAllSystemManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<SystemManager>> getAllSystemManager() {
		return systemManagerService.getAllSystemManager();
	}
	
	@RequestMapping(value = "/registerInstitutionManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionManager> register(@RequestBody @Valid InstitutionManager sm, @RequestParam(value="id") Long id) {
		return systemManagerService.registerInstitutionManager(sm, id);
	}
	
	@RequestMapping(value = "/registerSystem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager> registerSystem(@RequestBody @Valid SystemManager sm) {
		return systemManagerService.registerSystemManager(sm);
	}
	
	@RequestMapping(value = "/registerInstitution", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Institution> registerInstitution(@RequestBody @Valid Institution r) {
		return systemManagerService.registerInstitution(r);
	}
	
	@RequestMapping(value = "/updateinstitution", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Institution> updateinstitution(@RequestBody @Valid Institution r) {
		return systemManagerService.updateinstitution(r);
	}
	
	@RequestMapping(value = "/deleteinstitution", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Institution> deleteinstitution(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeinstitution(r_id);
	}
	@RequestMapping(value = "/deleteSystemManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager>  deleteSystemManager(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeSystemManager(r_id);
	}
	
	@RequestMapping(value = "/deleteFunManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<FunManager>  deleteFunManager(@RequestParam(value="id") Long fun_manager_id) {
		return systemManagerService.removeFunManager(fun_manager_id);
	}
	
	@RequestMapping(value = "/deleteinstitutionManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<InstitutionManager> deleteinstitutionManager(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeinstitutionManager(r_id);
	}
}
