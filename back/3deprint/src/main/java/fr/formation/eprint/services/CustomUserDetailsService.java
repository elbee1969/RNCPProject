package fr.formation.eprint.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.dtos.CustomUserInfoDto;

public interface CustomUserDetailsService extends UserDetailsService {
	
    CustomUserInfoDto getCurrentUserInfo(Long id);

	

	   List<CustomUserInfoDto> getAll();



	void deleteOne(Long id);

    
    //UserInfoDto getById(Long id);
    //UserDto create(UserCreateDto dto);
}
