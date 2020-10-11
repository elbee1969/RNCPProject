package fr.formation.eprint.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserCreateDto {

//	private final String message = "Must contains at least 6 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special char";
//    private final String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*~{}&.,§+=°_();/]).{6,40}$";
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

    private AddressCreateDto address;

    private AlbumCreateDto album;

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

    public AlbumCreateDto getAlbum() {
	return album;
    }

    public void setAlbum(AlbumCreateDto album) {
	this.album = album;
    }

    public String getPattern() {
	return getPattern();
    }

}
