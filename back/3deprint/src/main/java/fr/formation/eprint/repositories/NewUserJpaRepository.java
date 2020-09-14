package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserAuthViewDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.entities.CustomUser;

public interface NewUserJpaRepository extends JpaRepository<CustomUser, Long>{
	
	   Optional<UserCreateViewDto> getById(Long id); 
}
