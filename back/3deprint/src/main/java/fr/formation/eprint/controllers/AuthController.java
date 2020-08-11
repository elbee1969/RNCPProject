package fr.formation.eprint.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.apiFlow.request.LoginRequest;
import fr.formation.eprint.apiFlow.request.SignupRequest;
import fr.formation.eprint.apiFlow.response.JwtResponse;
import fr.formation.eprint.apiFlow.response.MessageResponse;
import fr.formation.eprint.entities.ERole;
import fr.formation.eprint.entities.Role;
import fr.formation.eprint.entities.User;
import fr.formation.eprint.repositories.RoleRepository;
import fr.formation.eprint.repositories.UserRepository;
import fr.formation.eprint.security.jwt.JwtUtils;
import fr.formation.eprint.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {



	@Autowired
	JwtUtils jwtUtils;
	
	private final UserDetailsServiceImpl service;
	protected AuthController(UserDetailsServiceImpl service) {
		this.service = service;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return service.signin(loginRequest);
	}

	

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return service.signUp(signUpRequest);
	}


}
