package ftn.isa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.entity.users.UserRank;

@Repository
public interface UserRankRepository extends CrudRepository<UserRank, Long> {

}
