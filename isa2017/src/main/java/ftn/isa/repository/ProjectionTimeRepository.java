package ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.entity.Projection;
import ftn.isa.entity.ProjectionTime;

@Repository
public interface ProjectionTimeRepository extends CrudRepository<ProjectionTime, Long> {
	
	@Query("select pt from ProjectionTime pt where pt.projection=?1")
	List<ProjectionTime> getAllProjectionTimeForProjection(Projection p);
}
