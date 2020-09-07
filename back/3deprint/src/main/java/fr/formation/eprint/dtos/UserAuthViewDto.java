package fr.formation.eprint.dtos;

import java.util.Set;

import fr.formation.eprint.entities.Role;

public interface UserAuthViewDto {
	
	  	Long getId();

	    String getUsername();
	    
	    String getFirstname();

	    String getLastname();

	    String getPassword();
	    
	    String getEmail();

	    Set<Role> getRoles();
	    
	    Long getAlbumId();

	    boolean isEnabled();

	    boolean isAccountNonExpired();

	    boolean isAccountNonLocked();

	    boolean isCredentialsNonExpired();
	}

