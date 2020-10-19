package fr.formation.eprint.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.config.ResourceServerConfig;
import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.dtos.CustomUserInfoDto;
import fr.formation.eprint.services.CustomUserDetailsService;

/**
 * @param <CustomUser>
 * @see ResourceServerConfig#configure(HttpSecurity)
 */
@RestController
@RequestMapping("/private") // "/api/private/*"
public class PrivateController<CustomUser> {

    /**
     * Accessible with "ROLE_USER".
     *
     * @return "Hello user!"
     */
    @PreAuthorize("hasRole('USER')") // == @Secured("ROLE_USER")
    @GetMapping("/user/{id}")
    public String user() {
	Long id = SecurityHelper.getUserId();
	String name = SecurityHelper.getUsername();
	return "Hello " + name + " ! your id is : " + id;
    }
//    @GetMapping("/user")
//    public Map<String, String> user(Principal user) {
//        return Collections.singletonMap("message", "user is: "+user().toString());
//    }

    @GetMapping("/user")
    public String[] user(Principal user) {
	Long id = SecurityHelper.getUserId();
	String str = String.valueOf(id);
	String name = SecurityHelper.getUsername();
	String[] tab;
	tab = new String[2];
	tab[0] = str;
	tab[1] = name;

	return tab;
    }

    /**
     * Accessible with "ROLE_ADMIN".
     *
     * @return "Hello admin!"
     */
    @PreAuthorize("hasRole('ADMIN')") // == @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin() {
	return "Hello admin!";
    }

    /**
     * Accessible if fully authenticated (not anonymous).
     *
     * @return "Hello fully authenticated!"
     */
    @GetMapping("/authenticated")
    public String secured() {
	return "Hello fully authenticated!";
    }

    private CustomUserDetailsService customUserService;

    protected PrivateController(CustomUserDetailsService customUserService) {
	this.customUserService = customUserService;
    }

    /**
     * 
     * @return all users
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CustomUserInfoDto> getAll() {
	return customUserService.getAll();
    }

    /**
     * 
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
	customUserService.deleteOne(id);
    }

}
