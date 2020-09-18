package fr.formation.eprint.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;

@Service
public class ImageStorageService {
	@Autowired
	  private ImageRepository imageRepository;
	
	

	
	public Image store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	    Image imageModel = new Image(file.getBytes(), file.getContentType(), fileName ) ;

	    return imageRepository.save(imageModel);
	  }

	  public Image getFile(String id) {
	    return imageRepository.findById(id).get();
	  }
	  
	  public Stream<Image> getAllFiles() {
	    return imageRepository.findAll().stream();
	  }
	}