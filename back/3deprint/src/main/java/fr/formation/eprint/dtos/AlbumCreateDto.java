package fr.formation.eprint.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import fr.formation.eprint.entities.CustomUser;

public class AlbumCreateDto {
	
	
	@NotNull
	@Max(20)
	private CustomUser customuser;

	public AlbumCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomUser getCustomuser() {
		return customuser;
	}

	public void setCustomuser(CustomUser customuser) {
		this.customuser = customuser;
	}


	
	

	
	

}
