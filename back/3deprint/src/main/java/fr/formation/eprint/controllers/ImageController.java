package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImageViewDto;
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
@RequestMapping("/private") // "/api/private/*"
public class ImageController {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
   NewUserJpaRepository userRepository;

	private final ImageStorageService imageStorageService;

	protected ImageController(ImageStorageService imageStorageService) {
		this.imageStorageService = imageStorageService;
	}
/**
 * 
 * @param file
 * @throws IOException
 * load one image in db
 * 
 */
	/*
	@PostMapping("/upload")
	public void uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {

		imageStorageService.store(file);
	}
*/
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
	 * get all images
	 */
	@GetMapping("/images")
	public List<ImageViewDto> getAll() {
		return imageStorageService.getAll();
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
	
	
	/*
	@GetMapping("/files")
	public ResponseEntity<List<ImageResponse>> getListFiles() {
		List<ImageResponse> files = imageStorageService.getAllFiles().map(image -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(image.getName()).toUriString();

			return new ImageResponse(image.getName(), fileDownloadUri, image.getType(), image.getData().length,
					image.getId());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
*/
	/*
	@GetMapping("/ownedfiles")
	public ResponseEntity<List<ImageGetDto>> getListOwnedFiles() {
		List<ImageGetDto> files = imageStorageService.getAllFiles().map(image -> {
			Long userId = SecurityHelper.getUserId();
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(image.getName()).toUriString();
			return new ImageGetDto(image.getId(), image.getName(), image.getType(), fileDownloadUri, userId);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
*/


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
