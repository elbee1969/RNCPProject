package fr.formation.eprint.dtos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;

import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.BooleanConverter;
import fr.formation.eprint.entities.Role;

public class UserDto {
	private String username;
//    private String email;
//    private String password;
	private String firstname;
	private String lastname;
//	private Set<Role> roles;
//	private boolean enabled;
//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//	private Album album;
	
	public UserDto() {

	}
//
	public String getFirstname() {
		return firstname;
	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
	public String getLastname() {
		return lastname;
	}

//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
	public String getUsername() {
		return username;
	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public UserDto(String username) {
//		super();
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}

//	public void setPassword(String password) {
//		this.password = password;
//	}
}	