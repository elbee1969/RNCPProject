package fr.formation.eprint.dtos;

/**
 * A projection of a {@code User} for user info.
 */
public interface UserInfoDto {

    Long getId();

    String getUsername();
    
    String getEmail();

    String getFirstname();

    String getLastname();
    
}