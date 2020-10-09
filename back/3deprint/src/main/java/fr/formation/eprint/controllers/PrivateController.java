package fr.formation.eprint.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.formation.eprint.config.ResourceServerConfig;
import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.response.ImageResponse;
import fr.formation.eprint.response.MessageResponse;
import fr.formation.eprint.services.CustomUserDetailsService;
import fr.formation.eprint.services.ImageStorageService;

/**
 * @param <CustomUser>
 * @see ResourceServerConfig#configure(HttpSecurity)
 */
@RestController
@RequestMapping("/private") // "/api/private/*"
public class PrivateController<CustomUser> {

    /**
     * Accessible with "ROLE_USER".
     *
     * @return "Hello user!"
     */
    @PreAuthorize("hasRole('USER')") // == @Secured("ROLE_USER")
    @GetMapping("/user/{id}")
    public String user() {
	Long id = SecurityHelper.getUserId();
	String name = SecurityHelper.getUsername();
	return "Hello " + name + " ! your id is : " + id;
    }
//    @GetMapping("/user")
//    public Map<String, String> user(Principal user) {
//        return Collections.singletonMap("message", "user is: "+user().toString());
//    }

    @GetMapping("/user")
    public String[] user(Principal user) {
	Long id = SecurityHelper.getUserId();
	String str = String.valueOf(id);
	String name = SecurityHelper.getUsername();
	String[] tab;
	tab = new String[2];
	tab[0] = str;
	tab[1] = name;

	return tab;
    }

    /**
     * Accessible with "ROLE_ADMIN".
     *
     * @return "Hello admin!"
     */
    @PreAuthorize("hasRole('ADMIN')") // == @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin() {
	return "Hello admin!";
    }

    /**
     * Accessible if fully authenticated (not anonymous).
     *
     * @return "Hello fully authenticated!"
     */
    @GetMapping("/authenticated")
    public String secured() {
	return "Hello fully authenticated!";
    }

    @Autowired
    private CustomUserDetailsService customUserService;

    protected PrivateController(CustomUserDetailsService customUserService) {
	this.customUserService = customUserService;
    }

    /**
     * 
     * @return all users
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomUserInfoDto> getAll() {
	return customUserService.getAll();
    }

    /**
     * 
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
	customUserService.deleteOne(id);
    }

    /**
     * part of the controller dedicated to the fields management
     * 
     */
    @Autowired
    private ImageStorageService storageService;
    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
//    public ImageModel uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {
//        ImageModel img = new ImageModel( file.getBytes(),file.getContentType(),file.getOriginalFilename());
//        final ImageModel savedImage = imageRepository.save(img);
//        System.out.println("Image saved");
//        return savedImage;
//    }
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
	String message = "";
	try {
	    storageService.store(file, null);

	    message = "Uploaded the file successfully: " + file.getOriginalFilename();
	    return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	} catch (Exception e) {
	    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
	}
    }

    @GetMapping("/files")
    public ResponseEntity<List<ImageResponse>> getListFiles() {
	List<ImageResponse> files = storageService.getAllFiles().map(image -> {
	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
		    .path(image.getName()).toUriString();

	    return new ImageResponse(image.getName(), fileDownloadUri, image.getType(), image.getData().length);
	}).collect(Collectors.toList());

	return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Image> getFile(@PathVariable Long id) {
	Image image = storageService.getFile(id);

	return ResponseEntity.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
		.body(image);
    }

}
