package fr.formation.eprint.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;

public interface ImageStorageService {


    void delete(Long id);


    List<ImageViewDto> findAll();

    Stream<Image> getAllFiles();

    ImageViewDto getOne(Long id);

    Image getAllImage();

    Image getFile(Long id);

    Image store(MultipartFile file) throws IOException;


	List<ImageViewDto> getAll();


	List<ImageGetDto> getAllByUserId();


	void deleteOne(Long id);


	public void init();

	  public void save(MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();





}