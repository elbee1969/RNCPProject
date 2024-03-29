package fr.formation.eprint.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.services.BillService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/bills") // "/api/private/*"
public class BillController {

	@Autowired
	private BillService billService;

	/**
	 * 
	 * @param id
	 * @param status
	 */
	@PreAuthorize("hasRole('ADMIN')") // == @Secured("ROLE_USER")
	@GetMapping("/create/{id}/{status}")
	public void createBill(@PathVariable("id") Long id, @PathVariable("status") Status status) {
		billService.create(id, status);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')") // == @Secured("ROLE_USER & "ROLE ADMIN")
	@GetMapping("/bill/{id}/{status}")
	public List<BillAdminViewDto> getAllById(@PathVariable("id") Long id, @PathVariable("status") Status status) {
		return billService.getAllByIdAndStatus(id, status);
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')") // == @Secured("ROLE_USER & "ROLE ADMIN")
	@GetMapping("/viewbills/{status}")
	public List<BillAdminViewDto> getAllBills(@PathVariable("status") Status status) {
		return billService.getAll(status);
	}
}
