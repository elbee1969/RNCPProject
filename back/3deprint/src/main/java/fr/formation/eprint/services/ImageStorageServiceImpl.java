package fr.formation.eprint.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.ImageAdminGetDto;
import fr.formation.eprint.dtos.ImageCreateViewDto;
import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImagePatchDto;
import fr.formation.eprint.dtos.ImageValidatedDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.exception.AccountNotFoundException;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

	private final Path roots = Paths.get("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads");

	private ImageRepository imageRepository;
	private NewUserJpaRepository userRepository;
	private ModelMapper mapper;

	protected ImageStorageServiceImpl(ImageRepository imageRepository, NewUserJpaRepository userRepository,
			ModelMapper mapper) {
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public List<ImageAdminGetDto> getAllByUser() {
		List<Image> images = imageRepository.getAllImage();
		List<ImageAdminGetDto> imagesToReturn = images.stream().map(image -> mapper.map(image, ImageAdminGetDto.class))
				.collect(Collectors.toList());
		return imagesToReturn;
	}

	@Override
	public ImageViewDto getOne(Long id) {

		return imageRepository.getById(id);
	}

	/**
	 * Update image status and quantity
	 * @return 
	 */
	//@Transactional
	@Override
	public void update(Long id, @Valid ImagePatchDto dto) {

		Image image = imageRepository.findById(id).get();
		mapper.map(dto, image);
		imageRepository.save(image);
	}

	/**
	 * Update image status
	 * @return 
	 */
	//@Transactional
	@Override
	public void updateV(Long id, @Valid ImageValidatedDto dto) {
		Image image = imageRepository.findById(id).get();
		mapper.map(dto, image);
		imageRepository.save(image);
	}
	
	@Override
	public Image getAllImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getFile(Long id) {
		final Optional<Image> retrievedImage = imageRepository.findById(id);
		Image img = new Image(decompressZLib(retrievedImage.get().getData()), retrievedImage.get().getName(),
				retrievedImage.get().getOwnerName(), retrievedImage.get().getType(), retrievedImage.get().getUrl(),
				retrievedImage.get().getStatus(), retrievedImage.get().getQuantity(),
				retrievedImage.get().getCustomUser());
		return img;
	}

	public Stream<Image> getAllFiles() {

		return imageRepository.findAll().stream();
	}

	/**
	 * save image in DB and in user directory
	 */
	@Override
	public BodyBuilder store(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		if (fileName.endsWith(".stl") || fileName.endsWith(".STL")) {
			Long userId = SecurityHelper.getUserId();
			CustomUser customUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
			String user = customUser.getUsername();
			Path url = Paths.get(roots + "\\" + user);
			Image image0 = new Image();
			Image image = new Image(compressZLib(file.getBytes()), fileName, user, file.getContentType(),
					url.toString(), image0.setStatus(Status.I), image0.setQuantity(1), customUser);
			File existFile = new File(url.toString() + "\\" + fileName);
			if (!existFile.exists() && !existFile.isDirectory()) {
				Files.copy(file.getInputStream(), url.resolve(file.getOriginalFilename()));
				imageRepository.save(image);
			} else {
				throw new IOException("Cette image existe déjà !");
			}
		}
		return ResponseEntity.status(HttpStatus.OK);
	}

	/**
	 * compress the image bytes before storing it in the database
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] compressZLib(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	/**
	 * uncompress the image bytes before returning it to the angular application
	 */
	public static byte[] decompressZLib(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException | DataFormatException ioe) {
		}
		return outputStream.toByteArray();
	}

	/**
	 * return a list of images owned by user
	 */
	public List<ImageGetDto> getAllByUserId(Status status) {
		Long customUserId = SecurityHelper.getUserId();
		List<Image> images = imageRepository.getAllImageByUserId(customUserId, status);
		List<ImageGetDto> imagesToReturn = images.stream().map(image -> mapper.map(image, ImageGetDto.class))
				.collect(Collectors.toList());
		return imagesToReturn;
	}

	/**
	 * Delete image from DB and user directory
	 */
	@Override
	public void deleteOne(Long id) throws IOException {
		// TODO Auto-generated method stub
		// Image value = imageRepository.findById(id);
		Long userId = SecurityHelper.getUserId();
		CustomUser customUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
		String userName = customUser.getUsername();

		Optional<Image> image = imageRepository.findById(id);
		String imageName = image.get().getName();
		System.out.println("nom image : " + imageName);
		// Files.(Paths.get("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads\\"+userName+"\\"+imageName));
		Path fileToDeletePath = Paths
				.get("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads\\" + userName + "\\" + imageName);
		Files.delete(fileToDeletePath);
		imageRepository.deleteById(id);

	}

	/*
	 * new *********************************************************
	 */

	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

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
