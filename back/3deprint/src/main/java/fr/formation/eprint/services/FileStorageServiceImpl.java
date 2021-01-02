package fr.formation.eprint.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Image3D;
import fr.formation.eprint.repositories.FileRepository;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;



@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path root = Paths.get("uploads");
	
	 private final FileRepository fileRepository;
	    private final NewUserJpaRepository userRepository;
	    private final ModelMapper mapper;

	    protected FileStorageServiceImpl(FileRepository fileRepository, NewUserJpaRepository userRepository, ModelMapper mapper) {
		this.fileRepository = fileRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	    }

	  @Override
	  public void init() {
	    try {
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public void save(@RequestParam("file") MultipartFile file) {
	    try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
			    Long userId = SecurityHelper.getUserId();
			    CustomUser customUser = userRepository.getOne(userId);
			    String url = root+"\\"+customUser.getUsername();
			    Image3D image = new Image3D( fileName, url, customUser);
			    Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			    fileRepository.save(image);
			}
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }

	  /*
	  @Override
	    public Image store(@RequestParam("file") MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
		    Long userId = SecurityHelper.getUserId();
		    CustomUser customUser = userRepository.getOne(userId);
		    Image image = new Image(compressZLib(file.getBytes()), fileName, file.getContentType(), customUser);
		    return imageRepository.save(image);
		}
		return (Image) ResponseEntity.status(HttpStatus.OK);
	    }
	  
	  */
	  
	  
	  
	  
	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
}
