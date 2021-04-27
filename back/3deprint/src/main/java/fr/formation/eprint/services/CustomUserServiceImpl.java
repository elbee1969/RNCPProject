package fr.formation.eprint.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
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
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.RoleJpaRepository;

@Service
public class CustomUserServiceImpl implements CustomUserService {

	private final PasswordEncoder passwordEncoder;
	private final CustomUserJpaRepository userJpaRepository;
	private final RoleJpaRepository roleJpaRepository;
	private ModelMapper mapper;

	protected CustomUserServiceImpl(PasswordEncoder passwordEncoder, CustomUserJpaRepository userRepository,
			RoleJpaRepository roleJpaRepository, ModelMapper mapper) {
		this.passwordEncoder = passwordEncoder;
		this.userJpaRepository = userRepository;
		this.roleJpaRepository = roleJpaRepository;
		this.mapper = mapper;
	}

	/**
	 * Create a new customUser
	 */
	@Override
	public UserDto create(UserCreateDto dto) {
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		Set<Role> role = new HashSet<>();
		role.add(roleJpaRepository.findByDefaultRole(true));
		Address address = new Address();
		CustomUser user = new CustomUser(dto.getUsername(), dto.getEmail(), encodedPassword, dto.getLastname(),
				dto.getFirstname(), role, address, true, true, true, true);
		createUserDir(dto.getUsername());
		CustomUser newUser = userJpaRepository.save(user);
		return mapper.map(newUser, UserDto.class);
	}

	/**
	 * create a personal directory with userName in path of local downloaded files
	 * "C:\\Users\\utilisateur\\Documents\\GitHub\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads"
	 * "H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads"
	 * 
	 * @param username
	 */
	private final Path root = Paths.get("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads");

	private void createUserDir(String username) {
		File directory = new File(root + "\\" + username);
		if (directory.exists()) {
			System.out.println("A folder with name '" + username + "' is already exist in the path " + directory);
		} else {
			directory.mkdir();
		}
	}

	/**
	 * load user by userName and if not found throws UsernameNotFoundException
	 * (Spring contract)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserAuthDto user = userJpaRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("no user found with username: " + username));
		return new CustomUserDetails(user);
	}

	/**
	 * delete a customUser
	 */
	@Override
	public void deleteOne(Long id) {

	}

	/**
	 * get info of customUser by is id and throws ResourceNotFoundException (restful
	 * practice)
	 */
	@Override
	public CustomUserInfoDto getCurrentUserInfo(Long id) {
		return userJpaRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
	}

	/**
	 * Method use for annotation @UniqueCustomUser
	 */
	@Override
	public boolean isUsernameValid(String username) {
		return !userJpaRepository.existsByUsername(username);
	}

	@Override
	public boolean isEmailValid(String email) {
		return !userJpaRepository.existsByEmail(email);
	}

}
