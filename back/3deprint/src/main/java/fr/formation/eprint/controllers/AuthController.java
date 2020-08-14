package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.apiFlow.request.LoginRequest;
import fr.formation.eprint.apiFlow.request.SignupRequest;
import fr.formation.eprint.security.jwt.JwtUtils;
import fr.formation.eprint.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
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
