package ftn.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.users.InstitutionManager;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {

	@Query("select sum(g.gradeOfInstitution)/count(g.gradeOfInstitution) from Institution r inner join r.grades as g where r.id = ?1")
	Double getgradeForInstitution(Long t);
	
	@Query("select r from Institution r inner join r.institutionManagers as rm where rm = ?1")
	Institution getByManager(InstitutionManager t);
}
