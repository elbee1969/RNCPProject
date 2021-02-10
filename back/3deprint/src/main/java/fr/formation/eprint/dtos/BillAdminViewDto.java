package fr.formation.eprint.dtos;

import java.time.LocalDate;

public interface BillAdminViewDto {

	Long getId();

	CustomUserIdNameDto getCustomUser();

	LocalDate getBillDate();

	double getTotalPriceHT();

	double getTotalPriceTTC();

	double getTotalWeight();

	int getTotalItem();

	String getStatus();

}
