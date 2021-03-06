package fr.formation.eprint.dtos;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;

public class UserCreateDto {
	
	
	
//	private final String message = "Must contains at least 6 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special char";
//    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";
//    @Pattern(regexp = pattern, message = message)
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastname;
    

    private AlbumCreateDto album;
    
	
	

	public UserCreateDto(String username, String email, String password, String firstname, String lastname,	AlbumCreateDto album) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.album = album;
	}


	public AlbumCreateDto getAlbum() {
		return album;
	}


	public void setAlbum(AlbumCreateDto album) {
		this.album = album;
	}


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMessage() {
		return getMessage();
	}

	public String getPattern() {
		return getPattern();
	}

}
