package ftn.isa.servis;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminSis;
import ftn.isa.model.korisnici.AdminUs;


public interface AdminSisServis {
	
	ResponseEntity<AdminSis> registerAdminSis(AdminSis sm);

	ResponseEntity<AdminUs> registerAdminUs(AdminUs sm, Long param);
	
	ResponseEntity<Ustanova> registerUstanova(Ustanova r);
	
	ResponseEntity<Ustanova> removeUstanova(Long r_id);

	ResponseEntity<AdminSis> removeAdminSis(Long r_id);

	ResponseEntity<AdminUs> removeAdminUs(Long r_id);

	ResponseEntity<List<Ustanova>> getAllUstanova();

	ResponseEntity<List<AdminUs>> getAdminUsForUstanova(Long id);

	ResponseEntity<List<AdminSis>> getAllAdminSis();

	ResponseEntity<AdminSis> updateAdminSis(AdminSis sm);

	ResponseEntity<Ustanova> updateUstanova(Ustanova r);


}
