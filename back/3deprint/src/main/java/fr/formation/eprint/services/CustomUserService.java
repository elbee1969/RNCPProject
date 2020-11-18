package fr.formation.eprint.services;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;

public interface CustomUserService {

    boolean isValid(String username);

    void deleteOne(Long id);

    UserDto create(@Valid UserCreateDto dto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    CustomUserInfoDto getCurrentUserInfo(Long id);

	UserDto update(Long id, @Valid UserAuthDto dto);

}
