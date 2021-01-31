package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.CustomUser;

public interface OrderAdminViewDto {
	
	long getId();
	
	String getName();
	
	int getQuantity();
	
	Double getTotalPrice();
	
	Double getTotalWeight();
	
	String getTimeToPrint();
	
	CustomUserIdDto getCustomUser();

}
