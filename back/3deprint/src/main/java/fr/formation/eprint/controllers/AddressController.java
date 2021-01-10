package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.services.AddressService;
import fr.formation.eprint.services.CustomUserService;

@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // == must be USER or ADMIN
@RequestMapping("/address") // "/api/address/*"
public class AddressController {

	private AddressService addressService;
	protected AddressController(AddressService addressService, CustomUserService customUserService) {
		this.addressService = addressService;
	}

	/**
	 * get one address
	 */
	@GetMapping("/address/{id}")
	public AddressViewDto getOneAddress(@PathVariable Long id) {
		return addressService.getOne(id);
	}

	/**
	 * 
	 * @param id
	 * @param dto update one address
	 * 
	 */

	@PatchMapping("/update/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody AddressPatchDto dto) {
		addressService.update(id, dto);
	}

}
