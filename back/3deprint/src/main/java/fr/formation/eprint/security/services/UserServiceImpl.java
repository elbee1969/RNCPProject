package fr.formation.eprint.security.services;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.AlbumCreateDto;
import fr.formation.eprint.dtos.AlbumCreateViewDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserCreateViewDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Album;
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.repositories.AlbumJpaRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.repositories.RoleJpaRepository;
import fr.formation.eprint.repositories.UserJpaRepository;

@Service
public class UserServiceImpl implements UserService {
	
    private final PasswordEncoder passwordEncoder;
    
    private final NewUserJpaRepository userJpaRepository;

    private final RoleJpaRepository roleJpaRepository;

    

    private ModelMapper mapper;
    
    protected UserServiceImpl(PasswordEncoder passwordEncoder, NewUserJpaRepository userRepository, RoleJpaRepository roleJpaRepository, AlbumJpaRepository albumJpaRepository) {
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
  public UserCreateViewDto create(UserCreateDto dto) {
  	String encodedPassword = passwordEncoder.encode(dto.getPassword());
  	Set<Role> role = new HashSet<>();
  	role.add(roleJpaRepository.findByDefaultRole(true));
	
	CustomUser user = new CustomUser(dto.getUsername(),dto.getEmail(),encodedPassword,dto.getLastname(), dto.getFirstname(), role,
  	        true, true, true, true,dto.getAlbum());
  	CustomUser newUser = userJpaRepository.save(user);
  	return mapper.map(newUser, UserCreateViewDto.class);
  }
//  @Override
//  public AlbumCreateViewDto create(AlbumCreateDto dto) {
//	  Album album = new Album(dto.getCustomuser());
//	  Album newAlbum = albumJpaRepository.save(album);
//	return mapper.map(newAlbum, AlbumCreateViewDto.class);
//	}
//  
  
  
	@Override
	public void deleteOne(Long id) {
		// TODO Auto-generated method stub
		
	}


}
