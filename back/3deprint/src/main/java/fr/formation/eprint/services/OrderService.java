package fr.formation.eprint.services;

import java.util.List;

import javax.validation.Valid;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.Order;

public interface OrderService {

	void create(@Valid OrderCreateDto dto);

	List<OrderAdminViewDto> getAll();


}
