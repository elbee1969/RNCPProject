package fr.formation.eprint.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.FileDBDto;
import fr.formation.eprint.dtos.FileDBViewDto;
import fr.formation.eprint.entities.ImageModel;

public interface FileService {
	
	void create(FileDBDto dto);

    void delete(Long id);

    ImageModel getOne(String id);

    List<FileDBViewDto> findAll();

	ImageModel store(MultipartFile file);



	List<FileDBViewDto> getAll();

	Object getAllFiles();
	

}
