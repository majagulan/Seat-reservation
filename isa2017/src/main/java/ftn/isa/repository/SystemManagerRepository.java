package ftn.isa.repository;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.users.SystemManager;

public interface SystemManagerRepository extends CrudRepository<SystemManager, Long> {

	SystemManager findByEmail(String email);
	
	
}
