package fr.formation.eprint.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.entities.Role;


/**
 * A custom {@code UserDetails} for Spring authentication contract and custom
 * properties we want in the token (such as the id).
 */
public class CustomUserDetails extends User {

    private static final long serialVersionUID = 7534801726443735155L;

    private Long id;

    public CustomUserDetails(CustomUserAuthDto user) {
	super(user.getUsername(), user.getPassword(),user.isEnabled(),
		user.isAccountNonExpired(), user.isCredentialsNonExpired(),
		user.isAccountNonLocked(), buildAuthorities(user.getRoles()));
	id = user.getId();
    }

  
	private static Set<GrantedAuthority> buildAuthorities(Set<Role> roles) {
	return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode()))
		.collect(Collectors.toUnmodifiableSet());
    }

    public Long getId() {
	return id;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", authorities=" + getAuthorities()
		+ ", password=[PROTECTED], username=" + getUsername()
		+ ", enabled=" + isEnabled() + ", accountNonExpired="
		+ isAccountNonExpired() + ", accountNonLocked="
		+ isAccountNonLocked() + ", credentialsNonExpired="
		+ isCredentialsNonExpired() + "}";
    }
}