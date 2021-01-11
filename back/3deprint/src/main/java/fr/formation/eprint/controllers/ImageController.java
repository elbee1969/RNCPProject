package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImagePatchDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.ImageAdminGetDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Image3D;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.response.ImageResponse;
import fr.formation.eprint.response.MessageImage3DResponse;
import fr.formation.eprint.response.Response;
import fr.formation.eprint.services.ImageStorageService;

@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // == must be USER or ADMIN
@RequestMapping("/image") // "/api/private/*"
public class ImageController {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
   NewUserJpaRepository userRepository;

	private final ImageStorageService imageStorageService;

	protected ImageController(ImageStorageService imageStorageService) {
		this.imageStorageService = imageStorageService;
	}


	@PostMapping("/upload")
	  public ResponseEntity<MessageImage3DResponse> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
	    String message = "";

	    imageStorageService.store(file);

	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new MessageImage3DResponse(message));
	   
	  }
	
	/**
	 * 
	 * @return
	 * get all images by admin with C status
	 */
	@GetMapping("/images")
	public List<ImageAdminGetDto> getAll() {
		return imageStorageService.getAllByUser();
	}

	/**
	 * 
	 * @return
	 * get all images from a user
	 */
	@GetMapping("/ownedImages")
	public List<ImageGetDto> getAllById() {
		return imageStorageService.getAllByUserId();
	}

/**
 * 
 * @param id
 * @return one image
 */
	@GetMapping("/image/{id}")
	public ImageViewDto getImage(@PathVariable Long id) {
		 String dir = System.getProperty("user.dir") + "\\uploads";
		System.out.println("répertoire : " + dir);
		return imageStorageService.getOne(id); 
	}
	
	
	@PatchMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody ImagePatchDto dto) {
		imageStorageService.update(id, dto);
	}


    @DeleteMapping("/deleteImage/{id}")
    public void deleteImage(@PathVariable("id") Long id) throws IOException {
    	imageStorageService.deleteOne(id);
    }
    
    /*
     * manage images in directory
     */
    	@PostMapping("/upload3d")
    	  public ResponseEntity<MessageImage3DResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    	    String message = "";
    	    try {
    	    	imageStorageService.save(file);

    	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
    	      return ResponseEntity.status(HttpStatus.OK).body(new MessageImage3DResponse(message));
    	    } catch (Exception e) {
    	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
    	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageImage3DResponse(message));
    	    }
    	  }
    
    
    	@GetMapping("/image3ds")
    	  public ResponseEntity<List<Image3D>> getListImage3Ds() {
    	    List<Image3D> image3Ds = imageStorageService.loadAll().map(path -> {
    	      String filename = path.getFileName().toString();
    	      String url = MvcUriComponentsBuilder
    	          .fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();
    	      Long userId = SecurityHelper.getUserId();
    		    CustomUser customUser = userRepository.getOne(userId);
    	      return new Image3D(filename, url, customUser);
    	    }).collect(Collectors.toList());

    	    return ResponseEntity.status(HttpStatus.OK).body(image3Ds);
    	  }

    	  @GetMapping("/image3ds/{filename:.+}")
    	  @ResponseBody
    	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    	    Resource file = imageStorageService.load(filename);
    	    return ResponseEntity.ok()
    	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    	  }
}
