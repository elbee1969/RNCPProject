package fr.formation.eprint.controllers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.config.SecurityHelper;



/**
 * @see ResourceServerConfig#configure(HttpSecurity)
 */
@RestController
@RequestMapping("/public") // "/api/public/*"
public class PublicController {

    /**
     * Accessible for anyone even anonymous.
     *
     * @return "Hello anyone!"
     */
    @GetMapping("/all")
    public String hello() {
	return "Bonjour visiteur!";
    }
    
    @GetMapping("/login")
    public String user() {
    	Long id = SecurityHelper.getUserId();
    	String name = SecurityHelper.getUsername();
	return "Hello " + name + " ! your id is : " + id;
    //public ResponseEntity<UserInfoDto> get(final UserInfoDto user) {
    //    return ResponseEntity.ok(user);
    }
    
    
}