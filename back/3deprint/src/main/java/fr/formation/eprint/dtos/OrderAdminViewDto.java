package fr.formation.eprint.dtos;

public interface OrderAdminViewDto {

	long getId();

	String getName();

	int getQuantity();

	Double getTotalPrice();

	Double getTotalWeight();

	String getTimeToPrint();

	String getStatus();

	CustomUserIdDto getCustomUser();

}
