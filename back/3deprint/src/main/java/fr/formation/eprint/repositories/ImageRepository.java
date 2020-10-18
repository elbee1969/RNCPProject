package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.ImageCreateViewDto;
import fr.formation.eprint.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByname(String name);

    @Query("SELECT i.id FROM Image i INNER JOIN CustomUser u ON i.customUserId = u.id WHERE customUserId = :customUserId")
    Long findAllImagesByUserId(@Param("customUserId") Long id);

    Optional<Image> findById(Long id);

    Optional<ImageCreateViewDto> getById(Long id);

}