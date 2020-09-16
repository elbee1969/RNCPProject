package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.ImageModel;


public interface ImageRepository extends JpaRepository<ImageModel, String> {
	Optional<ImageModel> findByname(String id);

//	FileDB findById(Long id);

}