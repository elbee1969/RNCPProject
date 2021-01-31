package fr.formation.eprint.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.services.OrderService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/order") // "/api/private/*"
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public void createOrder(@Valid @RequestBody OrderCreateDto dto) {
		orderService.create(dto);
	}
	@GetMapping("/vieworders")
	public List<OrderAdminViewDto> getAll() {
		return orderService.getAll();
	}
	
}
