package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.AddressCreateDto;
import fr.formation.eprint.dtos.AddressViewDto;

public interface AddressService {

	void update(Long id, @Valid AddressCreateDto dto);


	AddressViewDto getOne(Long id);

}
