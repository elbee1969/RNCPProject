package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByname(String name);

    Optional<Image> findById(Long id);

}