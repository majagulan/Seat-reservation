package ftn.isa.servis;

import org.springframework.http.ResponseEntity;

import ftn.isa.model.TerminProjekcije;

public interface AdminUsServis {

	ResponseEntity<TerminProjekcije> dodajNoviTerminProjekcije(TerminProjekcije p, Long rest_id);
		
	
}
