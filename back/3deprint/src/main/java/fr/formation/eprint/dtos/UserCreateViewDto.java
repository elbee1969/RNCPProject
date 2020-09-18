package fr.formation.eprint.dtos;

import java.util.Set;

import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;

public interface UserCreateViewDto {
	

	String getUsername();
    
	String getEmail();
	
	String getPassword();
	
	Set<Role> getRoles();
	
    String getFirstname();

    String getLastname();
 
    boolean isEnabled();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();
    
    public void getAlbum(Album album);
    
    
    
}
