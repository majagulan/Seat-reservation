package ftn.isa.servisImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminSis;
import ftn.isa.model.korisnici.AdminUs;
import ftn.isa.repozitorijum.AdminSisRepozitorijum;
import ftn.isa.repozitorijum.AdminUsRepozitorijum;
import ftn.isa.repozitorijum.KorisnikRepozitorijum;
import ftn.isa.repozitorijum.UstanovaRepozitorijum;
import ftn.isa.servis.AdminSisServis;

@Service
@Transactional
public class AdminSisServisImpl implements AdminSisServis{
	
	@Autowired
	private AdminSisRepozitorijum systemManagerRepository;

	@Autowired
	private KorisnikRepozitorijum userRepository;

	@Autowired
	private AdminUsRepozitorijum restaurantManagerRepository;

	@Autowired
	private UstanovaRepozitorijum restaurantRepository;

	@Override
	public ResponseEntity<AdminSis> registerAdminSis(AdminSis sm) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<AdminSis>(this.systemManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<AdminSis> updateAdminSis(AdminSis sm) {
		AdminSis temp = this.systemManagerRepository.findOne(sm.getId());
		if (!temp.getEmail().equals(sm.getEmail()))
			if (this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<AdminSis>(this.systemManagerRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AdminUs> registerAdminUs(AdminUs sm, Long restaurant_id) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		Ustanova r = this.restaurantRepository.findOne(restaurant_id);
		sm.setUstanova(r);
		AdminUs rs = this.restaurantManagerRepository.save(sm);
		return new ResponseEntity<AdminUs>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ustanova> registerUstanova(Ustanova r) {
		return new ResponseEntity<Ustanova>(this.restaurantRepository.save(r), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ustanova> updateUstanova(Ustanova r) {
		Ustanova temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setUstanovaIme(r.getUstanovaIme());
		return new ResponseEntity<Ustanova>(this.restaurantRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ustanova> removeUstanova(Long r_id) {
		this.restaurantRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AdminSis> removeAdminSis(Long r_id) {
		this.systemManagerRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AdminUs> removeAdminUs(Long r_id) {
		this.restaurantManagerRepository.delete(r_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Ustanova>> getAllUstanova() {
		return new ResponseEntity<List<Ustanova>>((List<Ustanova>) this.restaurantRepository.findAll(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AdminUs>> getAdminUsForUstanova(Long id) {
		return new ResponseEntity<List<AdminUs>>((List<AdminUs>) this.restaurantManagerRepository
				.findByUstanova(this.restaurantRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AdminSis>> getAllAdminSis() {
		return new ResponseEntity<List<AdminSis>>((List<AdminSis>) this.systemManagerRepository.findAll(),
				HttpStatus.OK);
	}

}
