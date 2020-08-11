package fr.formation.eprint.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserReadDTO implements DTOEntity {
	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
