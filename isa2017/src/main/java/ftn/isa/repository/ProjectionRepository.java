package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Projection;
import ftn.isa.entity.Reservation;

public interface ProjectionRepository extends CrudRepository<Projection, Long> {

	@Query("select p from Institution r inner join r.menu as p where r.id= ?1")
	List<Projection> getProjectionsForInstitution(Long t);
	
	@Query("select p from Institution r inner join r.menu as p where r.id= ?2 and p.id = ?1")
	Projection seeIfBelongsToinstitution(Long id, Long res_id);
	
	@Query("select p from Institution r inner join r.menu as p where p.projectionName = ?1 and r.id = ?2")
	List<Projection> findProjectionByInstitutionAndName(String name, Long id );
	
	@Query("select o.projection from Reservation r inner join r.orders as o where r=?1")
	Projection findProjectionByReservation(Reservation r);
}
