package fr.formation.eprint.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.dtos.OrderPatchDto;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.services.OrderService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/order") // "/api/private/*"
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 
	 * @param dto
	 */
	@PostMapping
	public void createOrder(@Valid @RequestBody OrderCreateDto dto) {
		orderService.create(dto);
	}

	/**
	 * get all orders with status = I
	 * 
	 * @return
	 */
	@GetMapping("/vieworders")
	public List<OrderAdminViewDto> getAll() {
		return orderService.getAll();
	}

	/**
	 * get order by id client and specified status
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@GetMapping("/vieworders/{id}/{status}")
	public List<OrderDto> getAllById(@PathVariable("id") Long id, @PathVariable("status") Status status) {
		return orderService.getAllByIdAndStatus(id, status);
	}

	@GetMapping("/vieworder/{id}")
	public OrderAdminViewDto getOrder(@PathVariable("id") Long id) {
		return orderService.getOne(id);
	}

	/**
	 * 
	 * @param id
	 * @param dto
	 */
	@PatchMapping("/updatevalidated/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody OrderPatchDto dto) {
		orderService.update(id, dto);
	}
}
