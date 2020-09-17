package fr.formation.eprint.dtos;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;

public class AlbumCreateDto {
	
	
    private List<Image> fileDBs;
	

	public AlbumCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Image> getFileDBs() {
		return fileDBs;
	}


	public void setFileDBs(List<Image> fileDBs) {
		this.fileDBs = fileDBs;
	}

	

	
	

	
	

}
