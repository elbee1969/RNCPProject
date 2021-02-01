package fr.formation.eprint.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;

public class BillCreateDto {
	
	private CustomUser customUser;
	
	private UUID billRef;

	private LocalDate orderDate;

	private List<Order> orders;

	private double totalPrice;

	
	
	public BillCreateDto() {

	}

	public BillCreateDto(CustomUser customUser, UUID billRef, LocalDate orderDate, List<Order> orders,
			double totalPrice) {
		super();
		this.customUser = customUser;
		this.billRef = billRef;
		this.orderDate = orderDate;
		this.orders = orders;
		this.totalPrice = totalPrice;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

	public UUID getBillRef() {
		return billRef;
	}

	public void setBillRef(UUID billRef) {
		this.billRef = billRef;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


}
