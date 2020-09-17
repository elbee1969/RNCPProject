package fr.formation.eprint.services;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.repositories.RoleJpaRepository;

@Service
public class CustomUserServiceImpl implements CustomUserService {
	
    private final PasswordEncoder passwordEncoder;
    private final NewUserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private ModelMapper mapper;
    
    @Autowired
    protected CustomUserServiceImpl(PasswordEncoder passwordEncoder, NewUserJpaRepository userRepository, RoleJpaRepository roleJpaRepository, AlbumJpaRepository albumJpaRepository) {
		// TODO Auto-generated constructor stub
    	this.passwordEncoder = passwordEncoder;
    	this.userJpaRepository = userRepository;
    	this.roleJpaRepository = roleJpaRepository;
   	}

	@Override
	public boolean isValid(String username) {
		return false;
	}

  @Override
  public UserDto create(UserCreateDto dto) {
  	String encodedPassword = passwordEncoder.encode(dto.getPassword());
  	Set<Role> role = new HashSet<>();
  	role.add(roleJpaRepository.findByDefaultRole(true));
  	
  	Album album = new Album();
	
	CustomUser user = new CustomUser(dto.getUsername(),dto.getEmail(),encodedPassword,dto.getLastname(), dto.getFirstname(), role,
  	        true, true, true, true, album);
  	CustomUser newUser = userJpaRepository.save(user);
  	return mapper.map(newUser, UserDto.class);
  }

  
	@Override
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		
	}



}
