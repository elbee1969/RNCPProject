package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	AddressViewDto getById(Long id);

	AddressPatchDto save(Class<AddressPatchDto> class1);


}
