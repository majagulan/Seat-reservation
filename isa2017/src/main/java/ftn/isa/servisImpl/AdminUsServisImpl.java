package ftn.isa.servisImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ftn.isa.model.TerminProjekcije;
import ftn.isa.servis.AdminUsServis;

public class AdminUsServisImpl implements AdminUsServis{

	@Override
	public ResponseEntity<TerminProjekcije> dodajNoviTerminProjekcije(TerminProjekcije p, Long rest_id) {
	
		return null;
		//return new ResponseEntity<TerminProjekcije>(this.terminProjekcijeRepozitorijum.sacuvaj(p),HttpStatus.OK);
	}

	

}
