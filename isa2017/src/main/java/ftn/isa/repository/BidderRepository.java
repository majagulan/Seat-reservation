package ftn.isa.repository;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.users.FunManager;

public interface BidderRepository extends CrudRepository<FunManager, Long> {

	
}
