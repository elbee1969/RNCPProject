package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;

public interface CustomUserService {

	boolean isValid(String username);
    
	void deleteOne(Long id);
		 
	UserDto create(@Valid UserCreateDto dto);

}
