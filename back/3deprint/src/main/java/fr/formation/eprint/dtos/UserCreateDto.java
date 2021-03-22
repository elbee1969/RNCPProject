package fr.formation.eprint.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.formation.eprint.annotations.UniqueCustomUser;

public class UserCreateDto {

	private final String message = "Le mot de passe doit contenir au moins 8 caractères, 1 majuscule, 1 minuscule, 1 nombre et 1 caractère spécial";
	private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{8,40}$";

	@NotBlank
	@Size(min = 3, max = 20)
	@UniqueCustomUser
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 8, max = 40)
	@Pattern(regexp = pattern, message = message)
	private String password;

	@NotBlank
	@Size(min = 2, max = 40)
	private String firstname;

	@NotBlank
	@Size(min = 2, max = 40)
	private String lastname;

	private AddressCreateDto address;

	public UserCreateDto() {
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

	public AddressCreateDto getAddress() {
		return address;
	}

	public void setAddress(AddressCreateDto address) {
		this.address = address;
	}

	public String getMessage() {
		return getMessage();
	}

	public String getPattern() {
		return getPattern();
	}

}
