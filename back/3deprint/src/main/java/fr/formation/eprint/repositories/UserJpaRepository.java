package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserInfoDto;
import fr.formation.eprint.entities.User;


public interface UserJpaRepository
	extends JpaRepository<User, Long> {

    /**
     * Retrieves a projected view of the {@code User} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<UserAuthDto> findByUsername(String username);

    /**
     * Retrieves a projected view of the current authenticated
     * {@code User}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<UserInfoDto> getById(Long id);
    
    boolean existsByUsername(String username);

}
