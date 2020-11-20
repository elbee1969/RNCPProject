package fr.formation.eprint.services;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.AddressCreateDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Address;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.repositories.AddressRepository;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.RoleJpaRepository;

@Service
public class AdressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	private final CustomUserJpaRepository userJpaRepository;
	private ModelMapper mapper;
	
	protected AdressServiceImpl(AddressRepository addressRepository, CustomUserJpaRepository userJpaRepository, ModelMapper mapper ) {
		this.addressRepository = addressRepository;
		this.userJpaRepository = userJpaRepository;
		this.mapper = mapper;
	};

	@Override
	public void update(Long id, @Valid AddressCreateDto dto) {
		// TODO Auto-generated method stub
		// findById returns an Optional object!
		Address address = addressRepository.findById(id).get();
		address.setNum(dto.getNum());
		address.setStreet(dto.getStreet());
		address.setTown(dto.getTown());
		address.setCountry(dto.getCountry());
		addressRepository.save(address);
		mapper.map(dto, Address.class);
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
