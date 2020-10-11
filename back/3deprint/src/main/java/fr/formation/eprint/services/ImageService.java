package fr.formation.eprint.services;

import java.util.List;

import javax.validation.Valid;

import fr.formation.eprint.dtos.ImageCreateDto;
import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Image;

public interface ImageService {

    Image create(@Valid ImageCreateDto dto);

    void delete(Long id);

    Image getOne(String id);

    List<ImageViewDto> findAll();

//    Image store(MultipartFile file);

    List<ImageViewDto> getAll();

    void create(@Valid ImageDto dto);

//    Object getAllFiles();

//    void populateAndSave(ImageDto dto, Image image);

//    ImageDto populateAndSave(@Valid ImageCreateDto dto);

//    ImageDto create(@Valid ImageCreateDto dto);
}
