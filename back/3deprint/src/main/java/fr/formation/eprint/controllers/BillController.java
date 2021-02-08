package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.BillCreateDto;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.services.BillService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/bill") // "/api/private/*"
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping("/{id}/{status}")
	public void createBill(@Valid @RequestBody BillCreateDto dto, @PathVariable("id") Long id,
			@PathVariable("status") Status status) {
		billService.create(dto, id, status);
	}

}
