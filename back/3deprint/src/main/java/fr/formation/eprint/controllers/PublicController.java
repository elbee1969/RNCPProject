package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.config.ResourceServerConfig;
import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.services.CustomUserService;

/**
 * @see ResourceServerConfig#configure(HttpSecurity)
 */
@RestController
@RequestMapping("/public") // "/api/public/*"
public class PublicController {

    /**
     * Accessible for anyone even anonymous.
     *
     * @return "Hello anyone!"
     */
    @GetMapping("/all")
    public String hello() {
	return "Bonjour visiteur!";
    }

    @GetMapping("/login")
    public String user() {
	Long id = SecurityHelper.getUserId();
	String name = SecurityHelper.getUsername();
	return "Hello " + name + " ! your id is : " + id;
	// public ResponseEntity<UserInfoDto> get(final UserInfoDto user) {
	// return ResponseEntity.ok(user);
    }

    /**
     * 
     * 
     * create a new user
     * 
     * 
     */
    @Autowired
    private CustomUserService userService;

    @PostMapping("/register")
    public UserDto create(@Valid @RequestBody UserCreateDto dto) {
	return userService.create(dto);
    }

//    @Autowired
//    private ImageStorageService storageService;
//
//    @PostMapping("/upload")
//
//    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
//	String message = "";
//	try {
//	    storageService.store(file);
//
//	    message = "Uploaded the file successfully: " + file.getOriginalFilename();
//	    return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
//	} catch (Exception e) {
//	    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
//	}
//    }
//
//    @GetMapping("/files/{id}")
//    public ResponseEntity<Image> getFile(@PathVariable Long id) {
//	Image image = storageService.getFile(id);
//
//	return ResponseEntity.ok()
//		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
//		.body(image);
//    }
}