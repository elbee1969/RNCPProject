package fr.formation.eprint.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.dtos.ImagePatchStatusDto;
import fr.formation.eprint.dtos.ImagePatchstatusAndQuantityDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Status;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findByname(String name);

	@Query("SELECT i FROM Image i INNER JOIN CustomUser u ON i.customUser.id = u.id WHERE i.customUser.id = :customUserId AND i.status = :status")
	public List<Image> getAllImageByUserId(@Param("customUserId") Long customUserId, @Param("status") Status status);

	@Override
	Optional<Image> findById(Long id);

	@Override
	List<Image> findAll();

	ImageViewDto save(ImageViewDto image);

	ImagePatchstatusAndQuantityDto save(ImagePatchstatusAndQuantityDto image);

	ImagePatchStatusDto save(ImagePatchStatusDto image);

	ImageViewDto getById(Long id);

	@Query("select i from Image i where i.status = :status order by i.ownerName ASC")
	public List<Image> getAllImages(@Param("status") Status status);

	void deleteById(long id);

	@Modifying
	@Query("DELETE From Image i WHERE i.customUser.id = :id")
	void deletImagesByUserId(Long id);

}