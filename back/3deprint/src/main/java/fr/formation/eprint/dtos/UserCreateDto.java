package fr.formation.eprint.dtos;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCreateDto {
	
	
	
//	private final String message = "Must contains at least 6 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special char";
//    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,30}$";
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotNull
    private Set<String> role;
    
    @NotNull
    @Size(max = 4)
	private Long albumId;
    
//    @Pattern(regexp = pattern, message = message)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastname;

	public UserCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public UserCreateDto(@NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotNull Set<String> role,
			@NotNull @Size(max = 4) Long albumId, @NotBlank @Size(min = 6, max = 40) String password,
			@NotBlank @Size(min = 2, max = 40) String firstname, @NotBlank @Size(min = 2, max = 40) String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.albumId = albumId;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
	

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
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
