package fr.formation.eprint.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.eprint.config.ApplicationUserDetails;
import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserInfoDto;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.UserJpaRepository;



@Service
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {

    private final UserJpaRepository repo;

    protected ApplicationUserDetailsServiceImpl(UserJpaRepository repo) {
	this.repo = repo;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	UserAuthDto user = repo.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new ApplicationUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public UserInfoDto getCurrentUserInfo(Long id) {
	return (UserInfoDto) repo.getById(id).orElseThrow(
		() -> new ResourceNotFoundException("with id:" + id));
    }
}
