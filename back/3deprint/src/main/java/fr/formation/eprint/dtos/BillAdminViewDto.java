package fr.formation.eprint.dtos;

import java.time.LocalDate;

public interface BillAdminViewDto {

	Long getId();

	CustomUserAllInfoDto getCustomUser();

	LocalDate getBillDate();

	Double getTotalPriceHT();

	Double getTotalPriceTTC();

	Double getTotalWeight();

	int getTotalItem();

	String getStatus();

}
