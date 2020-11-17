package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.dtos.ProfileUserInfosDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.entities.CustomUser;

@Repository
public interface NewUserJpaRepository extends JpaRepository<CustomUser, Long> {

    ProfileUserInfosDto getById(Long id);

    Object findByUsername(String username);
}
