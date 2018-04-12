package ftn.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.Institution;
import ftn.isa.entity.users.InstitutionManager;

public interface InstitutionManagerRepository extends CrudRepository<InstitutionManager, Long> {
		
	List<InstitutionManager> findByInstitution(Institution t);

}
