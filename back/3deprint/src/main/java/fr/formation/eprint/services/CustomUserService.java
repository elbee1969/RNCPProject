package fr.formation.eprint.services;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;

public interface CustomUserService {

	UserDto create(@Valid UserCreateDto dto);

	boolean isUsernameValid(String username);

	boolean isEmailValid(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	CustomUserInfoDto getCurrentUserInfo(Long id);

	void deleteOne(Long id);

}
