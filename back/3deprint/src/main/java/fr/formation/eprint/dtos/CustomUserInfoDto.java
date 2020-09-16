package fr.formation.eprint.dtos;

import java.util.Set;

import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;

/**
 * A projection of a {@code User} for user info.
 */
public interface CustomUserInfoDto {

    Long getId();

    String getUsername();
    
    String getEmail();

    String getFirstname();

    String getLastname();
    
    Set<Role> getRoles();
    
    AlbumCreateViewDto getAlbum();
    
}