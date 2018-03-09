package ftn.isa.repozitorijum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.model.Ustanova;

public interface UstanovaRepozitorijum  extends CrudRepository<Ustanova, Long>{
	
	@Query("select sum(g.gradeOfUstanova)/count(g.gradeOfUstanova) from Ustanova r inner join r.ocene as g where r.id = ?1")
	Double getGradeForUstanova(Long t);
	/*
	@Query("select r from Ustanova r inner join r.restaurantManagers as rm where rm = ?1")
	Ustanova getByManager(RestaurantManager t);
	*/

}
