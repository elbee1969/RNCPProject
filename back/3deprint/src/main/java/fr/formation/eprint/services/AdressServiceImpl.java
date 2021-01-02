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
	};

	@Override
	public void update(Long id, @Valid AddressPatchDto dto) {
		// findById returns an Optional object!
		Address address = addressRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		mapper.map(dto, address);
		addressRepository.save(address);
	}

	@Override
	public AddressViewDto getOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.getById(id);
	}

//	@Override
//	public UserDto update(Long id, @Valid UserAuthDto dto) {
//		// TODO Auto-generated method stub
//		CustomUser user = userJpaRepository.findById(id).get();
//		mapper.map(dto, CustomUser.class);
//		user.setUsername(dto.getUsername());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setLastname(dto.getLastname());
//		user.setFirstname(dto.getFirstname());
//		user.setRoles(dto.getRoles());
//		user.setAddress(dto.getAddress().getId(), dto.getAddress().getNum(), dto.getAddress().getStreet(),
//				dto.getAddress().getTown(), dto.getAddress().getCountry());
//		user.setAccountNonExpired(dto.isAccountNonExpired());
//		user.setAccountNonLocked(dto.isAccountNonLocked());
//		user.setCredentialsNonExpired(dto.isCredentialsNonExpired());
//		user.setEnabled(dto.isEnabled());
//		userJpaRepository.save(user);
//		return mapper.map(user, UserDto.class);
//	}
//
//	@Override
//	public void update(Long id, @Valid UserAuthDto dto) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public UserDto update(@Valid UserAuthDto dto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
