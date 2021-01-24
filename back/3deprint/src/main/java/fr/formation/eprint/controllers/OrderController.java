package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.services.OrderService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/order") // "/api/private/*"
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public Order createOrder(@Valid @RequestBody OrderCreateDto dto) {
		return orderService.create(dto);
	}
}
