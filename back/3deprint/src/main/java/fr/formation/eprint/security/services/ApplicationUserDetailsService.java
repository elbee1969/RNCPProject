package fr.formation.eprint.security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.dtos.UserInfoDto;

public interface ApplicationUserDetailsService extends UserDetailsService {
	
    UserInfoDto getCurrentUserInfo(Long id);
    
    
    //UserDto create(UserCreateDto dto);
}
