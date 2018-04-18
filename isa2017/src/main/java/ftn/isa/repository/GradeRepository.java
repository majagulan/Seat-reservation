package ftn.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Grade;
import ftn.isa.entity.Institution;
import ftn.isa.entity.Projection;
import ftn.isa.entity.Reservation;
import ftn.isa.entity.users.Guest;

public interface GradeRepository extends CrudRepository<Grade, Long> {
	
	Grade findOne(Long id);

	@Query("select g from Grade g where g.projection=?1 and g.guest=?2")
	public Grade findGradeByProjection(Projection p,Guest guest);
	
	@Query("select sum(g.gradeOfInstitution)/count(g.gradeOfInstitution) from Grade g where g.institution=?1")
	public Double getAverageGradeForInstitution(Institution i);
	
	@Query("select sum(g.gradeOfOrderItem)/count(g.gradeOfOrderItem) from Grade g where g.projection=?1")
	public Double getAverageGradeForProjection(Projection p );
	
	@Query("select g from Grade g where g.guest=?1 and g.reservation=?2")
	public Grade getGradeForUser(Guest g,Reservation r);
	
}
