package fr.formation.eprint.security.services;

import javax.validation.Valid;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;

public interface UserService {

	boolean isValid(String username);
    
	void deleteOne(Long id);
		 
	UserDto create(@Valid UserCreateDto dto);
}
