package fr.formation.eprint.dtos;

public class UserDto {
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private AddressCreateDto address;

	public UserDto() {
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getUsername() {
		return username;
	}

	public AddressCreateDto getAddress() {
		return address;
	}

	public void setAddress(AddressCreateDto address) {
		this.address = address;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

}