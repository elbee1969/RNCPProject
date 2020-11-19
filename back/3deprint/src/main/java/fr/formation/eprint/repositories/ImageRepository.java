package fr.formation.eprint.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByname(String name);

//    @Query(value = "SELECT i.id FROM image i INNER JOIN custom_user u ON i.custom_user_id = u.id WHERE custom_user_id = :customUserId", nativeQuery = true)

    @Query("SELECT i FROM Image i INNER JOIN CustomUser u ON i.customUser.id = u.id WHERE i.customUser.id = :customUserId")
    public List<Image> getAllImageByUserId(@Param("customUserId") Long customUserId);

    Optional<Image> findById(Long id);

    List<Image> findAll();

    ImageGetDto save(ImageGetDto img);

    ImageViewDto getById(Long id);

	List<ImageViewDto> getAllProjectedBy();









}