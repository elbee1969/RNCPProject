package fr.formation.eprint.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.ProfileUserInfosDto;
import fr.formation.eprint.entities.CustomUser;

@Repository
public interface CustomUserJpaRepository extends JpaRepository<CustomUser, Long> {

    /**
     * Retrieves a projected view of the {@code User} with given username.
     *
     * @param username a username
     * @return a projected view
     */
    Optional<CustomUserAuthDto> findByUsername(String username);

//    Optional<CustomUserAuthDto> findAll(String username);
    /**
     * Retrieves a projected view of the current authenticated {@code User}.
     *
     * @param id user id
     * @return a projected view
     */
    Optional<CustomUserInfoDto> getById(Long id);

    List<CustomUserInfoDto> getAllProjectedBy();

//    Optional<UserAuthViewDto> getById(Long id); 

//    boolean existsByUsername(String username);

}
