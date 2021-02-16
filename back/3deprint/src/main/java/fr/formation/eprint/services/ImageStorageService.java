package fr.formation.eprint.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageAdminGetDto;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImagePatchStatusDto;
import fr.formation.eprint.dtos.ImagePatchstatusAndQuantityDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Status;

public interface ImageStorageService {

	void delete(Long id);

	Stream<Image> getAllFiles();

	ImageViewDto getOne(Long id);

	Image getAllImage();

	Image getFile(Long id);

	BodyBuilder store(MultipartFile file) throws IOException;

	List<ImageAdminGetDto> getAllByUserAndStatus(Status status);

	List<ImageGetDto> getAllByUserId(Status status);

	void deleteOne(Long id) throws IOException;

	public void init();

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();

	void update(Long id, @Valid ImagePatchstatusAndQuantityDto dto);

	void updateS(Long id, @Valid ImagePatchStatusDto dto);
}
