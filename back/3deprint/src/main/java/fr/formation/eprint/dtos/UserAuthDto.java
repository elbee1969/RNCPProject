package fr.formation.eprint.dtos;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.formation.eprint.entities.Role;

public class UserAuthDto {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    private Set<Role> roles;

    @NotEmpty
    @Size(min = 2, max = 40)
    private String firstname;

    @NotEmpty
    @Size(min = 2, max = 40)
    private String lastname;

    private AddressCreateDto address;

    @NotNull
    private boolean enabled;

    @NotNull
    private boolean accountNonExpired;

    @NotNull
    private boolean accountNonLocked;

    @NotNull
    private boolean credentialsNonExpired;

    public UserAuthDto() {
	super();
	// TODO Auto-generated constructor stub
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

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
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

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
	this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	this.credentialsNonExpired = credentialsNonExpired;
    }

    public AddressCreateDto getAddress() {
	return address;
    }

    public void setAddress(AddressCreateDto address) {
	this.address = address;
    }

}
