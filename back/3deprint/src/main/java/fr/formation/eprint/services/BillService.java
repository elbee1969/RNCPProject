package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.BillCreateDto;

public interface BillService {

	public void create(Long id, @Valid BillCreateDto dto);

}
