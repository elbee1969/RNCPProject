package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.UserAuthViewDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.entities.CustomUser;


public interface CustomUserJpaRepository
	extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves a projected view of the {@code User} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code User}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUserInfoDto> getById(Long id);
    
    
//    Optional<UserAuthViewDto> getById(Long id); 
    
    
//    boolean existsByUsername(String username);

}
