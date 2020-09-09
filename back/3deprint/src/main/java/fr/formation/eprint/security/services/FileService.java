package fr.formation.eprint.security.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.FileDBDto;
import fr.formation.eprint.dtos.FileDBViewDto;
import fr.formation.eprint.entities.FileDB;

public interface FileService {
	
	void create(FileDBDto dto);

    void delete(Long id);

    FileDB getOne(Long id);

    List<FileDBViewDto> findAll();

	void store(MultipartFile file);



	List<FileDBViewDto> getAll();
	

}
