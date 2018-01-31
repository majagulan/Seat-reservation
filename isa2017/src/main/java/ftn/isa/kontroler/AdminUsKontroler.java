package ftn.isa.kontroler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.TerminProjekcije;
import ftn.isa.servis.AdminUsServis;

@RestController
@RequestMapping(value = "/adminusi")
public class AdminUsKontroler {
	
	@Autowired
	AdminUsServis adminusservis;
	/*
	@RequestMapping(value="/dodajTermin",
					method = RequestMethod.POST)
	public ResponseEntity<TerminProjekcije> dodajProjekciju(@RequestBody @Valid TerminProjekcije p,
			@RequestParam(value = "rest_id") Long rest_id) {
		return AdminUsServis.dodajNoviTerminProjekcije(p,rest_id);
	}
*/
}
