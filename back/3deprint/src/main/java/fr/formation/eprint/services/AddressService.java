package fr.formation.eprint.services;

import javax.validation.Valid;

import fr.formation.eprint.dtos.AddressCreateDto;
import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserDto;

public interface AddressService {

//	void update(Long id, @Valid UserAuthDto dto);

	AddressViewDto getOne(Long id);

//	UserDto update(@Valid UserAuthDto dto);

	void update(Long id, @Valid AddressPatchDto dto);

//	UserDto update(Long id, @Valid UserAuthDto dto);

}
