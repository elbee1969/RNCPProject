package fr.formation.eprint.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.eprint.config.CustomUserDetails;
import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.UserAuthViewDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;



@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
//    @Autowired
    private final CustomUserJpaRepository userRepo;

    protected CustomUserDetailsServiceImpl(CustomUserJpaRepository userRepo) {
    	
    	this.userRepo = userRepo;
    }


    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
    	CustomUserAuthDto user = userRepo.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
	return userRepo.getById(id).orElseThrow(
		() -> new ResourceNotFoundException("with id:" + id));
    }
	
  }
