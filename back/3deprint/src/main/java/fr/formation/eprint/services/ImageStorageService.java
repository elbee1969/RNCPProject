package fr.formation.eprint.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;

@Service
public class ImageStorageService {

    private ImageRepository imageRepository;

    private NewUserJpaRepository userRepository;

    protected ImageStorageService(ImageRepository imageRepository, NewUserJpaRepository userRepository) {
	this.imageRepository = imageRepository;
	this.userRepository = userRepository;

    }

    public Image store(MultipartFile file) throws IOException {
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
	    Long userId = SecurityHelper.getUserId();
	    CustomUser customUser = userRepository.getOne(userId);
	    Image imageModel = new Image(file.getBytes(), fileName, file.getContentType(), customUser);
	    return imageRepository.save(imageModel);
	} else {
	    return null;

	}

    }

    public Image getFile(Long id) {
	return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
	return imageRepository.findAll().stream();
    }

    public Long getAllOwnedFiles(Long customUserId) {
	// TODO Auto-generated method stub
	return imageRepository.findAllImagesByUserId(customUserId);
    }

}