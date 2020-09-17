package fr.formation.eprint.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.ImageDto;
import fr.formation.eprint.dtos.ImageViewDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;

public class FileServiceImpl implements FileService {
	
	private ImageRepository fileDBRepositoty;
	private AlbumJpaRepository albumRepository;
	
	
	protected FileServiceImpl (ImageRepository fileDBRepositoty,AlbumJpaRepository albumRepository) {
		this.fileDBRepositoty = fileDBRepositoty;
		this.albumRepository = albumRepository;
	}

	@Override
	public void create(ImageDto dto) {
		// TODO Auto-generated method stub
		Image fileDB = new Image();
		populateAndSave(dto, fileDB);
				
//		public FileDB store(MultipartFile file) throws IOException {
//		    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		    Album album = new Album();
////		    UserAuthDto.class.cast(album).getId();
//		    		
//			FileDB FileDB = new FileDB(fileName, file.getContentType(),fileName, file.getBytes(), (album).getId() ) ;
//
//		    return fileDBRepository.save(FileDB);
//		  }
		
	}

	private void populateAndSave(ImageDto dto, Image fileDB) {
		// TODO Auto-generated method stub
		fileDB.setName(dto.getName());
		fileDB.setData(dto.getData());
		fileDB.setType(dto.getType());
		//Album album = albumRepository.getOne(dto.getUserId());
//		fileDB.setAlbum(album);
		fileDBRepositoty.save(fileDB);
		
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


	@Override
	public Image store(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageViewDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAllFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
