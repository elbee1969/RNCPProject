package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Address;

public interface ProfileUserInfosDto {

	 Long getId();

	    String getUsername();

	    String getEmail();

	    String getFirstname();

	    String getLastname();
	    
	    Address getAddress() ;
	    
}
