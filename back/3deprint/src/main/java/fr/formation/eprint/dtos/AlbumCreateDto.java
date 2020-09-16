package fr.formation.eprint.dtos;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.ImageModel;

public class AlbumCreateDto {
	
	
    private List<ImageModel> fileDBs;
	

	public AlbumCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<ImageModel> getFileDBs() {
		return fileDBs;
	}


	public void setFileDBs(List<ImageModel> fileDBs) {
		this.fileDBs = fileDBs;
	}

	

	
	

	
	

}
