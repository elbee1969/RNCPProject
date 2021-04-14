package fr.formation.eprint.services;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.entities.Address;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.AddressRepository;
import fr.formation.eprint.repositories.CustomUserJpaRepository;

@Service
public class AdressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	private ModelMapper mapper;
	
	protected AdressServiceImpl(AddressRepository addressRepository, CustomUserJpaRepository userJpaRepository, ModelMapper mapper ) {
		this.addressRepository = addressRepository;
		this.mapper = mapper;
	}	@Override
	public void update(Long id, @Valid AddressPatchDto dto) {
		Address address = addressRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		mapper.map(dto, address);
		addressRepository.save(address);
	}

	/**
	 * get user address
	 */
	@Override
	public AddressViewDto getOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.getById(id);
	}


}
