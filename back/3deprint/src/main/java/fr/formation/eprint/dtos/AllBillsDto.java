package fr.formation.eprint.dtos;

import java.time.LocalDate;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Status;

public class AllBillsDto {

	private Long id;

	private CustomUser customUser;

	private LocalDate billDate;

	private double totalPriceHT;

	private double totalPriceTTC;

	private double totalWeight;

	private int totalItem;

	private Status status;

	public AllBillsDto() {

	}

	public AllBillsDto(Long id, CustomUser customUser, LocalDate billDate, double totalPriceHT, double totalPriceTTC,
			double totalWeight, int totalItem, Status status) {
		super();
		this.id = id;
		this.customUser = customUser;
		this.billDate = billDate;
		this.totalPriceHT = totalPriceHT;
		this.totalPriceTTC = totalPriceTTC;
		this.totalWeight = totalWeight;
		this.totalItem = totalItem;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public double getTotalPriceHT() {
		return totalPriceHT;
	}

	public void setTotalPriceHT(double totalPriceHT) {
		this.totalPriceHT = totalPriceHT;
	}

	public double getTotalPriceTTC() {
		return totalPriceTTC;
	}

	public void setTotalPriceTTC(double totalPriceTTC) {
		this.totalPriceTTC = totalPriceTTC;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
