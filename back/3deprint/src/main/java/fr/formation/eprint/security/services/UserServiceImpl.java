package fr.formation.eprint.security.services;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.entities.User;
import fr.formation.eprint.repositories.RoleJpaRepository;
import fr.formation.eprint.repositories.UserJpaRepository;

@Service
public class UserServiceImpl implements UserService {
	
    private final PasswordEncoder passwordEncoder;
    
    private final UserJpaRepository userJpaRepository;

    private final RoleJpaRepository roleJpaRepository;
    

    private ModelMapper mapper;
    
    protected UserServiceImpl(PasswordEncoder passwordEncoder, UserJpaRepository userRepository, RoleJpaRepository roleJpaRepository) {
		// TODO Auto-generated constructor stub
    	this.passwordEncoder = passwordEncoder;
    	this.userJpaRepository = userRepository;
    	this.roleJpaRepository = roleJpaRepository;
	}

	@Override
	public boolean isValid(String username) {
		// TODO Auto-generated method stub
		return false;
	}


  @Override
  public UserCreateDto create(UserCreateViewDto dto) {
  	String encodedPassword = passwordEncoder.encode(dto.getPassword());
  	Set<Role> role = new HashSet<>();
  	role.add(roleJpaRepository.findByDefaultRole(true));
  	Album album = new Album();
	User user = new User(dto.getUsername(),dto.getEmail(),encodedPassword, role, album ,
  	        true, true, true, true, dto.getFirstname(),dto.getLastname());
  	User newUser = userJpaRepository.save(user);
  	return mapper.map(newUser, UserCreateDto.class);
  }

	@Override
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		
	}

	

	
}
