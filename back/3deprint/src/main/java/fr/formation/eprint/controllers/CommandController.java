package fr.formation.eprint.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // == must be USER or ADMIN
@RequestMapping("/command") // "/api/private/*"
public class CommandController {

}
