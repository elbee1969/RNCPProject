package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.BillCreateDto;

public interface BillService {

	void create(@Valid BillCreateDto dto);

}
