package fr.formation.eprint.dtos;

import java.time.LocalDate;
import java.util.List;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Order;

public class BillCreateDto {

	private CustomUser customUser;

	private LocalDate billDate;

	private List<Order> orders;

	private double totalPriceHT;

	private double totalPriceTTC;

	private double totalWeight;

	private int totalItem;

	public BillCreateDto() {

	}

	public BillCreateDto(CustomUser customUser, LocalDate billDate, List<Order> orders, double totalPriceHT,
			double totalPriceTTC, double totalWeight, int totalItem) {
		super();
		this.customUser = customUser;
		this.billDate = billDate;
		this.orders = orders;
		this.totalPriceHT = totalPriceHT;
		this.totalPriceTTC = totalPriceTTC;
		this.totalWeight = totalWeight;
		this.totalItem = totalItem;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

}
