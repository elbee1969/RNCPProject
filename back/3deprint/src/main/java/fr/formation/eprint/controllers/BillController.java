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
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.services.BillService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/bill") // "/api/private/*"
public class BillController {
	@Autowired
	private BillService billService;
	
	@PostMapping("/create/{id}")
	public void createBill(@PathVariable("id") Long id, @Valid @RequestBody BillCreateDto dto) {
		billService.create(id,dto);
	}
}
