package fr.formation.eprint.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.AddressCreateDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.entities.Address;
import fr.formation.eprint.repositories.AddressRepository;

@Service
public class AdressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	
	protected AdressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	};

	@Override
	public void update(Long id, @Valid AddressCreateDto dto) {
		// TODO Auto-generated method stub
		// findById returns an Optional object!
		Address address = addressRepository.findById(id).get();
		populateAndSave(dto, address);
	}

	private void populateAndSave(@Valid AddressCreateDto dto, Address address) {
		// TODO Auto-generated method stub
		address.setNum(dto.getNum());
		address.setStreet(dto.getStreet());
		address.setTown(dto.getTown());
		address.setCountry(dto.getCountry());
		addressRepository.save(address);
	}

	@Override
	public AddressViewDto getOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.getById(id);
	}

}
