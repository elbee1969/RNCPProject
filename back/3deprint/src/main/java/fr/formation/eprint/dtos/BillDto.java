package fr.formation.eprint.dtos;

import java.time.LocalDate;

import fr.formation.eprint.entities.Status;

public class BillDto {

	private Long id;

	private Long customUserId;

	private LocalDate billDate;

	private double totalPriceHT;

	private double totalPriceTTC;

	private double totalWeight;

	private int totalItem;

	private Status status;

	public BillDto() {

	}

	public BillDto(Long id, Long customUserId, LocalDate billDate, double totalPriceHT, double totalPriceTTC,
			double totalWeight, int totalItem, Status status) {
		super();
		this.id = id;
		this.customUserId = customUserId;
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

	public Long getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(Long customUserId) {
		this.customUserId = customUserId;
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
