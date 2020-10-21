package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.response.ImageResponse;
import fr.formation.eprint.services.ImageService;

@RestController
@RequestMapping("/private") // "/api/private/*"
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    private NewUserJpaRepository userRepository;

    private final ImageService imageService;

    protected ImageController(ImageService imageService) {
	this.imageService = imageService;
    }

//    @PostMapping("/upload")
//    @ResponseStatus(HttpStatus.OK)
//    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//	imageService.store(file);
//    }

    @PostMapping("/upload")
    public void uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {

//	System.out.println("Original Image Byte Size - " + file.getBytes().length);
//	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	Long userId = SecurityHelper.getUserId();
//	CustomUser customUser = userRepository.getOne(userId);
//	Image image = new Image(compressZLib(file.getBytes()), fileName, file.getContentType(), customUser);
//
//	imageRepository.save(image);
//	return ResponseEntity.status(HttpStatus.OK);
	imageService.store(file);
    }

    @GetMapping("/files")
    public ResponseEntity<List<ImageResponse>> getListFiles() {
	List<ImageResponse> files = imageService.getAllFiles().map(image -> {
	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
		    .path(image.getName()).toUriString();

	    return new ImageResponse(image.getName(), fileDownloadUri, image.getType(), image.getData().length,
		    image.getId());
	}).collect(Collectors.toList());

	return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/ownedfiles")
    public ResponseEntity<List<ImageResponse>> getListOwnedFiles() {
	List<ImageResponse> files = imageService.getAllFiles().map(image -> {
	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/ownedfiles/")
		    .path(image.getName()).toUriString();
	    Long userId = SecurityHelper.getUserId();
	    return new ImageResponse(image.getName(), fileDownloadUri, image.getType(), image.getData().length, userId);
	}).collect(Collectors.toList());

	return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Image> getFile(@PathVariable Long id) {
	Image image = imageService.getFile(id);

	return ResponseEntity.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
		.body(image);
    }

}
