package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ustanova;
import ftn.isa.model.korisnici.AdminUs;


public interface AdminUsRepozitorijum  extends CrudRepository<AdminUs,Long>{
	
	List<AdminUs> findByUstanova(Ustanova t);

}
