package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import fr.formation.eprint.dtos.OrderViewItemDto;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.services.OrderService;

@RestController
@PreAuthorize("hasRole('ADMIN')") // == must be ADMIN
@RequestMapping("/orders") // "/api/private/*"
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
	@GetMapping("/viewordersI")
	public List<OrderAdminViewDto> getAllOrderI() {
		return orderService.getAllI();
	}

	/**
	 * 
	 * get all orders with status = A
	 * 
	 * @return
	 */
	@GetMapping("/viewordersA")
	public List<OrderAdminViewDto> getAllOrderA() {
		return orderService.getAllA();
	}

	/**
	 * get order by id client and specified status
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')") // == @Secured("ROLE_USER & "ROLE ADMIN")
	@GetMapping("/vieworders/{id}/{status}")
	public List<OrderDto> getAllById(@PathVariable("id") Long id, @PathVariable("status") Status status) {
		return orderService.getAllByUserIdAndStatus(id, status);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/vieworder/{id}")
	public OrderAdminViewDto getOrder(@PathVariable("id") Long id) {
		return orderService.getOne(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/viewitemsorder/{id}")
	public List<OrderViewItemDto> getItem(@PathVariable("id") Long id) {
		return orderService.getAllById(id);
	}

	/**
	 * 
	 * @param id
	 * @param dto
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')") // == @Secured("ROLE_USER & "ROLE ADMIN")
	@PatchMapping("/updatevalidated/{id}")
	public void update(@PathVariable("id") Long id, @Valid @RequestBody OrderPatchDto dto) {
		orderService.update(id, dto);
	}

	/**
	 * 
	 * @param id
	 * @throws IOException
	 */
	@PreAuthorize("hasRole('USER')") // == @Secured("ROLE_USER")
	@DeleteMapping("/deleteorder/{id}")
	public void deleteImage(@PathVariable("id") Long id) throws IOException {
		orderService.deleteOne(id);
	}

}
