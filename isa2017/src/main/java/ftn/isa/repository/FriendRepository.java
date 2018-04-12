package ftn.isa.repository;

import org.springframework.data.repository.CrudRepository;

import ftn.isa.entity.users.Friend;
import ftn.isa.entity.users.FriendId;

public interface FriendRepository extends CrudRepository<Friend, FriendId>{

}
