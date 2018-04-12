package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Projection;

public interface ProjectionRepository extends CrudRepository<Projection, Long> {

	@Query("select p from Institution r inner join r.menu as p where r.id= ?1")
	List<Projection> getProjectionsForInstitution(Long t);
	
	@Query("select p from Institution r inner join r.menu as p where r.id= ?2 and p.id = ?1")
	Projection seeIfBelongsToinstitution(Long id, Long res_id);
	
	@Query("select p from RequestOffer ro inner join ro.projections as p where ro.id= ?1")
	List<Projection> getProjectionsForRequestOffer(Long t);
	
	@Query("select p from Institution r inner join r.menu as p where p.projectionName = ?1 and r.id = ?2")
	List<Projection> findProjectionByInstitutionAndName(String name, Long id );
}
