package ftn.isa.service;

import ftn.isa.entity.users.User;

public interface UserService {
	
	User logIn(User user);
	
	
	User updateUser(User user);

}
