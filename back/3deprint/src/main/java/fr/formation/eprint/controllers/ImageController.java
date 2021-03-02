package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageAdminGetDto;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImagePatchStatusDto;
import fr.formation.eprint.dtos.ImagePatchstatusAndQuantityDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.exception.ImageAlreadyExistExeption;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.response.MessageImage3DResponse;
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
	public ResponseEntity<MessageImage3DResponse> uploadImage(@RequestParam("file") MultipartFile file)
			throws ImageAlreadyExistExeption, IOException {
		String message = "";

		imageStorageService.store(file);

		message = "Uploaded the file successfully: " + file.getOriginalFilename();
		return ResponseEntity.status(HttpStatus.OK).body(new MessageImage3DResponse(message));

	}

	/**
	 * 
	 * @return get all images of all users to be printed by admin with "Validated"
	 *         status (V)
	 */
	@GetMapping("/images/{status}")
	public List<ImageAdminGetDto> getAll(@PathVariable("status") Status status) {
		return imageStorageService.getAllByUserAndStatus(status);
	}

	/**
	 * 
	 * @return get all images from a user with "choosen" status ("C")
	 */
	@GetMapping("/ownedimages")
	public List<ImageGetDto> getAllByIdByStatusI() {
		return imageStorageService.getAllByUserId(Status.I);
	}

	/**
	 * 
	 * @return get all images selected to be printed from a user
	 */
	@GetMapping("/choosedimages")
	public List<ImageGetDto> getAllByIdByStatusC() {
		return imageStorageService.getAllByUserId(Status.C);
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

	/**
	 * update image status from status I to C or C to I
	 * 
	 * @param id
	 * @param dto
	 */
	@PatchMapping("/updatestatusandquantity/{id}")
	public void updatestatusandquantity(@PathVariable("id") Long id,
			@Valid @RequestBody ImagePatchstatusAndQuantityDto dto) {
		imageStorageService.update(id, dto);
	}

	/***
	 * update image status from status C to V and create an ordered
	 * 
	 * @param id
	 * @param dto
	 */
	@PatchMapping("/updatestatus/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody ImagePatchStatusDto dto) {
		imageStorageService.updateS(id, dto);
	}

	/**
	 * delete image from DB and directory
	 * 
	 * @param id
	 * @throws IOException
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

}
