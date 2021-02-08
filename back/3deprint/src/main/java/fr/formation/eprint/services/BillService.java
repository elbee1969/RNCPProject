package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.BillCreateDto;
import fr.formation.eprint.entities.Status;

public interface BillService {

	void create(@Valid BillCreateDto dto, Long id, Status status);

}
