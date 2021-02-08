package fr.formation.eprint.services;

import java.util.List;

import javax.validation.Valid;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.dtos.OrderPatchDto;
import fr.formation.eprint.entities.Status;

public interface OrderService {

	void create(@Valid OrderCreateDto dto);

	List<OrderAdminViewDto> getAll();

	List<OrderDto> getAllByIdAndStatus(Long id, Status status);

	void update(Long id, @Valid OrderPatchDto dto);

	OrderAdminViewDto getOne(Long id);

}
