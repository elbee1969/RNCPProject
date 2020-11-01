package fr.formation.eprint.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageGetDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private NewUserJpaRepository userRepository;
    private ModelMapper mapper;

    protected ImageServiceImpl(ImageRepository imageRepository, NewUserJpaRepository userRepository, ModelMapper mapper) {
	this.imageRepository = imageRepository;
	this.userRepository = userRepository;
	this.mapper = mapper;
    }

    @Override
    public void delete(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<ImageViewDto> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

//    @Override
//    public Image store(MultipartFile file) {
//	// TODO Auto-generated method stub
//	return null;
//    }

    @Override
    public List<ImageViewDto> getAll() {
	// TODO Auto-generated method stub
    	return imageRepository.getAllProjectedBy();
    }

//    @Override
//    public Object getAllFiles() {
//	// TODO Auto-generated method stub
//	return null;
//    }

    @Override
    public ImageViewDto getOne(Long id) {
	// TODO Auto-generated method stub
	return imageRepository.getById(id);
    }


    @Override
    public Image getAllImage() {
	// TODO Auto-generated method stub
	return null;
    }

    public Image getFile(Long id) {
	return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {

	return imageRepository.findAll().stream();
    }


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

//    @Override
//    public BodyBuilder uplaodImage(MultipartFile file) throws IOException {
//	// TODO Auto-generated method stub
//	System.out.println("Original Image Byte Size - " + file.getBytes().length);
//	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	Long userId = SecurityHelper.getUserId();
//	CustomUser customUser = userRepository.getOne(userId);
//	Image image = new Image(compressZLib(file.getBytes()), fileName, file.getContentType(), customUser);
//
//	imageRepository.save(image);
//	return ResponseEntity.status(HttpStatus.OK);
//    }

    // compress the image bytes before storing it in the database
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

    // uncompress the image bytes before returning it to the angular application
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

	public List<ImageGetDto> getAllByUserId() {

		Long customUserId = SecurityHelper.getUserId();
		List<Image> images = imageRepository.getAllImageByUserId(customUserId);
		List<ImageGetDto> imagesToReturn = images.stream()
		        .map(elt -> mapper.map(elt, ImageGetDto.class))
		        .collect(Collectors.toList());
		return imagesToReturn;
		
	}


}
