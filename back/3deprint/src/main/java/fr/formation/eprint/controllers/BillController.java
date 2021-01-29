package fr.formation.eprint.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/bill") // "/api/private/*"
public class BillController {

}