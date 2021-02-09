package fr.formation.eprint.dtos;

import java.time.LocalDate;

public interface BillAdminViewDto {

	Long getId();

	Long getCustomUserId();

	LocalDate getBillDate();

	double getTotalPriceHT();

	double getTotalPriceTTC();

	double getTotalWeight();

	int getTotalItem();

	String getStatus();

}
