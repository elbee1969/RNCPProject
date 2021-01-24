package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.Order;

public interface OrderService {

	Order create(@Valid OrderCreateDto dto);

}
