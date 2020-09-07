package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.AlbumViewDto;
import fr.formation.eprint.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	<Optional> AlbumViewDto getById(Long id);

}
