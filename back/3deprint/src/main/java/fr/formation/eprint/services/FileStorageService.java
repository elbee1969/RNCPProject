package fr.formation.eprint.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface FileStorageService {

	public void init();

	  public void save(MultipartFile file) throws IOException;

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();

}
