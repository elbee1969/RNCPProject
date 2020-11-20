package fr.formation.eprint.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.eprint.dtos.AddressCreateDto;
import fr.formation.eprint.dtos.AddressPatchDto;
import fr.formation.eprint.dtos.AddressViewDto;
import fr.formation.eprint.dtos.UserAuthDto;
import fr.formation.eprint.dtos.UserDto;
import fr.formation.eprint.entities.Address;
import fr.formation.eprint.services.AddressService;
import fr.formation.eprint.services.CustomUserService;

@RestController
@RequestMapping("/private") // "/api/private/*"
public class AddressController {

    
    private AddressService addressService;
    private CustomUserService customUserService;
    
    
    protected AddressController(AddressService addressService, CustomUserService customUserService) {
		this.addressService = addressService;
		this.customUserService = customUserService;
	}
	
    /**
     * get one address
     */
    @GetMapping("/address/{id}") 
	public AddressViewDto getOneAddress(@PathVariable Long id) {
		return addressService.getOne(id);
	}
    
    /**
     * 
     * @param id
     * @param dto
     * update one address
     * 
     */

//    @PatchMapping("/update")
//    public AddressPatchDto update(@Valid @RequestBody AddressCreateDto dto) {
//	return addressService.update(dto);
//    }
	
	
  @PatchMapping("/update/{id}")
  public void update(@PathVariable("id") Long id, @Valid @RequestBody AddressCreateDto dto) {
	addressService.update(id, dto);
  }
	
	
}
