package fr.formation.eprint.dtos;

import java.util.Set;

import fr.formation.eprint.entities.Role;

public interface UserCreateViewDto {
    Long getId();

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

}
