package fr.formation.eprint.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.ImageCreateDto;
import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private NewUserJpaRepository userRepository;

    protected ImageServiceImpl(ImageRepository imageRepository, NewUserJpaRepository userRepository) {
	this.imageRepository = imageRepository;
	this.userRepository = userRepository;
    }

    @Override
    public void create(@Valid ImageDto dto) {
	// TODO Auto-generated method stub
	Image image = new Image();
	populateAndSave(dto, image);
    }

    @Override
    public Image create(@Valid ImageCreateDto dto) {
	// TODO Auto-generated method stub
	Image image = new Image();
	image.setName(dto.getName());
	image.setData(dto.getData());
	image.setType(dto.getType());
	Long userId = SecurityHelper.getUserId();
	CustomUser user = userRepository.getOne(userId);
	image.setCustomUser(user);
	return imageRepository.save(image);
    }

    public void populateAndSave(ImageDto dto, Image image) {
	// TODO Auto-generated method stub
	image.setName(dto.getName());
	image.setData(dto.getData());
	image.setType(dto.getType());
	Long userId = SecurityHelper.getUserId();
	CustomUser user = userRepository.getOne(userId);
	image.setCustomUser(user);
	imageRepository.save(image);
    }

//    public Image store(MultipartFile file) throws IOException {
//	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
//	    Long userId = SecurityHelper.getUserId();
//	    CustomUser customUser = userRepository.getOne(userId);
//	    Image imageModel = new Image(file.getBytes(), fileName, file.getContentType(), customUser);
//	    return imageRepository.save(imageModel);
//	} else {
//	    return null;
//
//	}
//
//    }

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
	return null;
    }

//    @Override
//    public Object getAllFiles() {
//	// TODO Auto-generated method stub
//	return null;
//    }

    @Override
    public Image getOne(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Image getOne(Long id) {
	// TODO Auto-generated method stub
	return null;
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

    public List<Image> getAllOwnedFiles(Long customUserId) {
	// TODO Auto-generated method stub
	return imageRepository.findAllImagesByUserId(customUserId);
    }

    @Override
    public void store(MultipartFile file) throws IOException {
	// TODO Auto-generated method stub
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
	    Long userId = SecurityHelper.getUserId();
	    CustomUser customUser = userRepository.getOne(userId);
	    Image imageModel = new Image(file.getBytes(), fileName, file.getContentType(), customUser);
	    imageRepository.save(imageModel);
	}
    }

}
