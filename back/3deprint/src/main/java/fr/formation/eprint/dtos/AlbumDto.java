package fr.formation.eprint.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class AlbumDto {
	
	
	@NotNull
	@Max(20)
	private Long userId;

	public AlbumDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUser_id(Long userId) {
		this.userId = userId;
	}

	public AlbumDto(@NotNull @Max(20) Long userId) {
		super();
		this.userId = userId;
	}

	
	

}
