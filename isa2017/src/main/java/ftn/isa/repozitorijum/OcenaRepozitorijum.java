package ftn.isa.repozitorijum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ocena;
import ftn.isa.model.Pogledano;
import ftn.isa.model.korisnici.Posetilac;


public interface OcenaRepozitorijum  extends CrudRepository<Ocena, Long>{

	Ocena findOne(Long id);

	@Query("select g from Ocena g where g.projekcija=?1 and g.posetilac=?2")
	public Ocena findOcenaByProjekcija(Pogledano pogledano,Posetilac guest);

	
}
