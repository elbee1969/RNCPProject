package fr.formation.eprint.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;

@Service
public class ImageStorageService {
    @Autowired
    private ImageRepository imageRepository;
    private AlbumJpaRepository albumRepository;

    protected ImageStorageService(ImageRepository imageRepository, AlbumJpaRepository albumRepository) {
	this.imageRepository = imageRepository;
	this.albumRepository = albumRepository;
    }

    public Image store(MultipartFile file) throws IOException {
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".stl")) {
	    Long userId = SecurityHelper.getUserId();
	    Album album = albumRepository.getOne(userId);
	    Image imageModel = new Image(file.getBytes(), fileName, file.getContentType(), album);
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

}