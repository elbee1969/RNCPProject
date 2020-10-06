package fr.formation.eprint.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.eprint.config.CustomUserDetails;
import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;



@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	
    @Autowired
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


    @Override
    public List<CustomUserInfoDto> getAll() {
	return userRepo.getAllProjectedBy();
    }

    @Override
    public void deleteOne(Long id) {
	Optional<CustomUserInfoDto> value = userRepo.getById(id);
	if (value.isPresent()) {
		userRepo.deleteById(id);
	} else {
	    throw new AccountNotFoundException("Invalid id : " + id);
	}
    }

    
  }
