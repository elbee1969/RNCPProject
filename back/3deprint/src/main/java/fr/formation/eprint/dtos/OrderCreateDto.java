package fr.formation.eprint.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import fr.formation.eprint.entities.Address;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Ordered;

public class OrderCreateDto {

	private CustomUser customUser;

	private Address address;

	private UUID orderRef;

	private LocalDate orderDate;

	private String state;

	private List<Ordered> ordereds;

	private double totalPrice;

	public OrderCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderCreateDto(CustomUser customUser, Address address, UUID orderRef, LocalDate orderDate, String state,
			List<Ordered> ordereds, double totalPrice) {
		super();
		this.customUser = customUser;
		this.address = address;
		this.orderRef = orderRef;
		this.orderDate = orderDate;
		this.state = state;
		this.ordereds = ordereds;
		this.totalPrice = totalPrice;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UUID getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(UUID orderRef) {
		this.orderRef = orderRef;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Ordered> getItems() {
		return ordereds;
	}

	public void setItems(List<Ordered> ordereds) {
		this.ordereds = ordereds;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
