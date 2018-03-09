package ftn.isa.repozitorijum;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.korisnici.AdminSis;

public interface AdminSisRepozitorijum  extends CrudRepository<AdminSis, Long>{
	
	AdminSis findByEmail(String email);

}
