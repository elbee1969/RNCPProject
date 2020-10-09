package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.AlbumCreateViewDto;
import fr.formation.eprint.entities.Album;

public interface AlbumJpaRepository extends JpaRepository<Album, Long> {

    Optional<AlbumCreateViewDto> getById(Long id);

}
