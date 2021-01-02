package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Image3D;

@Repository
public interface FileRepository extends JpaRepository<Image3D, Long>{

	Image3D save(Image3D image);

	
	 @Query("SELECT i FROM Image3D i INNER JOIN CustomUser u ON i.customUser.id = u.id WHERE i.customUser.id = :customUserId")
	    public List<Image3D> getAllImageByUserId(@Param("customUserId") Long customUserId);
	
	
}
