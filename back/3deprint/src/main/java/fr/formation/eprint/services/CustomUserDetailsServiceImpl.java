package fr.formation.eprint.services;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.eprint.config.CustomUserDetails;
import fr.formation.eprint.dtos.CustomUserAuthDto;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.dtos.ProfileUserInfosDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.exception.AccountNotFoundException;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.NewUserJpaRepository;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

	private final CustomUserJpaRepository userRepo;
	private final NewUserJpaRepository newUserRepo;
	private final ImageRepository imageRepo;

	protected CustomUserDetailsServiceImpl(CustomUserJpaRepository userRepo, NewUserJpaRepository newUserRepo,
			ImageRepository imageRepo) {

		this.userRepo = userRepo;
		this.newUserRepo = newUserRepo;
		this.imageRepo = imageRepo;
	}

	// Throws UsernameNotFoundException (Spring contract)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserAuthDto user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("no user found with username: " + username));
		return new CustomUserDetails(user);
	}

	// Throws ResourceNotFoundException (restful practice)
	@Override
	public CustomUserInfoDto getCurrentUserInfo(Long id) {
		return userRepo.getById(id).orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
	}

	@Override
	public List<CustomUserInfoDto> getAll() {
		return userRepo.getAllProjectedBy();
	}

	@Transactional
	@Override
	public void deleteOne(Long id) {
		FileSystem fs = FileSystems.getDefault();
		// Path path =
		// fs.getPath("C:\\Users\\utilisateur\\Documents\\GitHub\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads");
		Path path = fs.getPath("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets\\uploads");
		// Long userId = SecurityHelper.getUserId();
		Optional<CustomUser> customUser = userRepo.findById(id);
		String userName = customUser.get().getUsername();
		Optional<CustomUserInfoDto> value = userRepo.getById(id);
		if (value.isPresent()) {
			System.out.println("name : " + userName);
			imageRepo.deletImagesByUserId(id);
			userRepo.deleteById(id);

			File index = new File(path + "\\" + userName);
			System.out.println("index : " + index);
			deleteDir(index);
		} else {
			throw new AccountNotFoundException("Invalid id : " + id);
		}
	}

	void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}

	@Override
	public ProfileUserInfosDto getOne(Long id) {
		return newUserRepo.getById(id);
	}

}
