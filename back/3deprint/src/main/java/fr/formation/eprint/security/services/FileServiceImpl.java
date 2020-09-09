package fr.formation.eprint.security.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.formation.eprint.dtos.FileDBDto;
import fr.formation.eprint.dtos.FileDBViewDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.FileDB;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.FileDBRepository;

public class FileServiceImpl implements FileService {
	
	private FileDBRepository fileDBRepositoty;
	private AlbumJpaRepository albumRepository;
	
	
	protected FileServiceImpl (FileDBRepository fileDBRepositoty,AlbumJpaRepository albumRepository) {
		this.fileDBRepositoty = fileDBRepositoty;
		this.albumRepository = albumRepository;
	}

	@Override
	public void create(FileDBDto dto) {
		// TODO Auto-generated method stub
		FileDB fileDB = new FileDB();
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

	private void populateAndSave(FileDBDto dto, FileDB fileDB) {
		// TODO Auto-generated method stub
		fileDB.setName(dto.getName());
		fileDB.setData(dto.getData());
		fileDB.setType(dto.getType());
		Album album = albumRepository.getOne(dto.getUserId());
//		fileDB.setAlbum(album);
		fileDBRepositoty.save(fileDB);
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

		

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<FileDBViewDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileDBViewDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDB getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
