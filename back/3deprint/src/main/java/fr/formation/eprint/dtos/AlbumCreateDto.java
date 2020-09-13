package fr.formation.eprint.dtos;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.FileDB;

public class AlbumCreateDto {
	
	
    private List<FileDB> fileDBs;
	

	public AlbumCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<FileDB> getFileDBs() {
		return fileDBs;
	}


	public void setFileDBs(List<FileDB> fileDBs) {
		this.fileDBs = fileDBs;
	}

	

	
	

	
	

}
