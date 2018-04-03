package ftn.isa.repozitorijum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ocena;
import ftn.isa.model.Pogledano;
import ftn.isa.model.korisnici.Posetilac;


public interface OcenaRepozitorijum  extends CrudRepository<Ocena, Long>{

	Ocena findOne(Long id);

	@Query("select o from Ocena o where o.pogledano=?1 and o.posetilac=?2")
	public Ocena findOcenaByProjekcija(Pogledano pogledano,Posetilac guest);

	
}
