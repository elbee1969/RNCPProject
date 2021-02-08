package fr.formation.eprint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.entities.Status;
import fr.formation.eprint.services.BillService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/bills") // "/api/private/*"
public class BillController {

	@Autowired
	private BillService billService;

	@GetMapping("/{id}/{status}")
	public void createBill(@PathVariable("id") Long id, @PathVariable("status") Status status) {
		billService.create(id, status);
	}

}
