package fr.formation.eprint.dtos;

import java.util.Set;

import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;

/**
 * A projection of a {@code CustomUser} for authentication.
 */
public interface CustomUserAuthDto {
	
		Long getId();

	    String getUsername();
	    
	    String getFirstName();
	    
	    String getLastName();
	    
	    String getEmail();

	    String getPassword();

	    Set<Role> getRoles();

	    boolean isEnabled();

	    boolean isAccountNonExpired();

	    boolean isAccountNonLocked();

	    boolean isCredentialsNonExpired();
	    
	    Album getAlbum();
	    
	    
}
