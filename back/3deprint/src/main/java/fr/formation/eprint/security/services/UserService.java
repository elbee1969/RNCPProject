package fr.formation.eprint.security.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.AlbumCreateDto;
import fr.formation.eprint.dtos.AlbumCreateViewDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.dtos.UserDto;

public interface UserService {

	boolean isValid(String username);
    
	void deleteOne(Long id);
	
	 //void create(UserCreateViewDto dto);
	 
	UserDto create(@Valid UserCreateDto dto);

	

//	AlbumCreateViewDto create(AlbumCreateDto dto);



}
