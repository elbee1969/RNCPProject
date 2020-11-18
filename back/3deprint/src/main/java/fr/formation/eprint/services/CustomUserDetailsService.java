package fr.formation.eprint.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.ProfileUserInfosDto;

public interface CustomUserDetailsService extends UserDetailsService {

	CustomUserInfoDto getCurrentUserInfo(Long id);

	List<CustomUserInfoDto> getAll();

	void deleteOne(Long id);

	ProfileUserInfosDto getOne(Long id);

}
