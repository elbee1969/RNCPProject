package fr.formation.eprint.services;

import java.util.List;

import javax.validation.Valid;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.ImageCreateDto;
import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;

public class ImageServiceImpl implements ImageService {

    private ImageRepository fileDBRepositoty;
    private AlbumJpaRepository albumRepository;

    protected ImageServiceImpl(ImageRepository fileDBRepositoty, AlbumJpaRepository albumRepository) {
	this.fileDBRepositoty = fileDBRepositoty;
	this.albumRepository = albumRepository;
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
	Album album = albumRepository.getOne(userId);
	image.setAlbum(album);
	return fileDBRepositoty.save(image);
    }

    public void populateAndSave(ImageDto dto, Image image) {
	// TODO Auto-generated method stub
	image.setName(dto.getName());
	image.setData(dto.getData());
	image.setType(dto.getType());
	Long userId = SecurityHelper.getUserId();
	Album album = albumRepository.getOne(userId);
	image.setAlbum(album);
	fileDBRepositoty.save(image);

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

//    @Override
//    public ImageDto populateAndSave(@Valid ImageCreateDto dto) {
//	// TODO Auto-generated method stub
//	return null;
//    }

//    @Override
//    public ImageDto create(@Valid ImageCreateDto dto) {
//	// TODO Auto-generated method stub
//	return null;
//    }

}
