package ftn.isa.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Projekcija;

public interface ProjekcijaRepozitorijum  extends CrudRepository<Projekcija, Long>{
	
	@Query("select p from Ustanova r inner join r.repertoar as p where r.id= ?1")
	List<Projekcija> getAllProjekcijaForUstanova(Long t);
	
	@Query("select p from Ustanova r inner join r.repertoar as p where r.id= ?2 and p.id = ?1")
	Projekcija seeIfBelongsToUstanova(Long id, Long res_id);

	@Query("select p from Ustanova r inner join r.repertoar as p where p.imeProjekcije = ?1 and r.id = ?2")
	List<Projekcija> findProjekcijaByUstanovaAndName(String name, Long id );

}
