package ftn.isa.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.users.InstitutionManager;

public interface InstitutionManagerRepository extends CrudRepository<InstitutionManager, Long> {
		
	List<InstitutionManager> findByInstitution(Institution t);
	
	@Query("select sum(o.projection.price) from Order o  where o.reservation.institution = ?1 and o.orderStatus='PAID' and o.date between ?2 and ?3")
	Double getEarningsForInstitution(Institution t, Date startDate, Date endDate);

}
