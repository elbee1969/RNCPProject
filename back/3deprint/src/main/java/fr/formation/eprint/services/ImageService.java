package fr.formation.eprint.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;

public interface ImageService {


    void delete(Long id);


    List<ImageViewDto> findAll();

    Stream<Image> getAllFiles();

    ImageViewDto getOne(Long id);

    Image getAllImage();

    Image getFile(Long id);

    Image store(MultipartFile file) throws IOException;


	List<ImageViewDto> getAll();


	List<ImageViewDto> getAllByUserId(Long id);








}
