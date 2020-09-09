package fr.formation.eprint.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.eprint.config.ApplicationUserDetails;
import fr.formation.eprint.dtos.UserAuthViewDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.dtos.UserInfoDto;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.UserJpaRepository;



@Service
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {
//    @Autowired
    private final UserJpaRepository userJpaRepository;

    protected ApplicationUserDetailsServiceImpl(UserJpaRepository userJpaRepository) {
    	
    	this.userJpaRepository = userJpaRepository;
    }


    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	UserAuthViewDto user = userJpaRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new ApplicationUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public UserInfoDto getCurrentUserInfo(Long id) {
	return (UserInfoDto) userJpaRepository.findById(id).orElseThrow(
		() -> new ResourceNotFoundException("with id:" + id));
    }

	
    }
