package fr.formation.eprint.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;

public interface FileService {

    void create(ImageDto dto);

    void delete(Long id);

    Image getOne(String id);

    List<ImageViewDto> findAll();

    Image store(MultipartFile file);

    List<ImageViewDto> getAll();

    Object getAllFiles();

}
