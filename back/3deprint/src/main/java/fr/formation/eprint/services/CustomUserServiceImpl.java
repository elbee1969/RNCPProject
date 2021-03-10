package fr.formation.eprint.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.formation.eprint.config.CustomUserDetails;
import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.UserCreateDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Address;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.exception.DuplicateEntryException;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.RoleJpaRepository;

@Service
public class CustomUserServiceImpl implements CustomUserService {

	private final Path root = Paths.get("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads");
	private final PasswordEncoder passwordEncoder;
	private final CustomUserJpaRepository userJpaRepository;
	private final RoleJpaRepository roleJpaRepository;
	private ModelMapper mapper;

	@Autowired
	protected CustomUserServiceImpl(PasswordEncoder passwordEncoder, CustomUserJpaRepository userRepository,
			RoleJpaRepository roleJpaRepository, ModelMapper mapper) {
		// TODO Auto-generated constructor stub
		this.passwordEncoder = passwordEncoder;
		this.userJpaRepository = userRepository;
		this.roleJpaRepository = roleJpaRepository;
		this.mapper = mapper;
	}

	@Override
	public boolean isValid(String username) {
		return false;
	}

	@Override
	public UserDto create(UserCreateDto dto) throws DuplicateEntryException {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		Set<Role> role = new HashSet<>();
		role.add(roleJpaRepository.findByDefaultRole(true));

		List<Image> images = new ArrayList<>();
		Address address = new Address();
		CustomUser user = new CustomUser(dto.getUsername(), dto.getEmail(), encodedPassword, dto.getLastname(),
				dto.getFirstname(), role, address, true, true, true, true);
		createUserDir(dto.getUsername());
		CustomUser newUser = userJpaRepository.save(user);
		return mapper.map(newUser, UserDto.class);
	}

	/**
	 * create a personal directory
	 * 
	 * @param username
	 */
	private void createUserDir(String username) {

		File directory = new File(root + "\\" + username);
		if (directory.exists()) {
			System.out.println("A folder with name '" + username + "' is already exist in the path " + directory);
		} else {
			directory.mkdir();
		}
	}

//Throws UsernameNotFoundException (Spring contract)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserAuthDto user = userJpaRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("no user found with username: " + username));
		return new CustomUserDetails(user);
	}

	@Override
	public void deleteOne(Long id) {

	}

	// Throws ResourceNotFoundException (restful practice)
	@Override
	public CustomUserInfoDto getCurrentUserInfo(Long id) {
		return userJpaRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
	}

}
