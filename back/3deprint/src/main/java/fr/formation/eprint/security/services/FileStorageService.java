package fr.formation.eprint.security.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserAuthViewDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.FileDB;
import fr.formation.eprint.repositories.FileDBRepository;

@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;
  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Album album = new Album();
    UserAuthViewDto.class.cast(album).getId();
    		
	FileDB FileDB = new FileDB(fileName, file.getContentType(),fileName, file.getBytes(), (album).getId() ) ;

    return fileDBRepository.save(FileDB);
  }

  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
}

