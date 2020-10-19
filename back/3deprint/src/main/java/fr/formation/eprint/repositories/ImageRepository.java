package fr.formation.eprint.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByname(String name);

    @Query(value = "SELECT i.id FROM Image i INNER JOIN CustomUser u ON i.custom_user_id = u.id WHERE custom_user_id = :custom_user_id", nativeQuery = true)
    List<Image> findAllImagesByUserId(@Param("custom_user_id") Long id);

    Optional<Image> findById(Long id);

    ImageGetDto save(ImageGetDto img);

//    Optional<ImageCreateViewDto> getById(Long id);

}