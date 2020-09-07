package fr.formation.eprint.security.services;

import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;

public interface UserService {

	 boolean isValid(String username);


	    
	    void deleteOne(Long id);



		UserCreateDto create(UserCreateViewDto dto);



}
