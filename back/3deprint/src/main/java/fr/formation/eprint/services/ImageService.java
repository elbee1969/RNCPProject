package fr.formation.eprint.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;

public interface ImageService {

//    Image create(@Valid ImageCreateDto dto);

    void delete(Long id);

    Image getOne(String id);

    List<ImageViewDto> findAll();

//    void store(MultipartFile file) throws IOException;

    List<ImageViewDto> getAll();

//    void create(@Valid ImageDto dto);

    Stream<Image> getAllFiles();

    Image getOne(Long id);

    Image getAllImage();

    Image getFile(Long id);

    Image store(MultipartFile file) throws IOException;

//    BodyBuilder uplaodImage(MultipartFile file) throws IOException;

    List<Image> getAllOwnedFiles();

}
