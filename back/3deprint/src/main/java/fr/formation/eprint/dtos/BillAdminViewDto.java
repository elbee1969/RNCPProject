package fr.formation.eprint.dtos;

import java.time.LocalDate;

import fr.formation.eprint.entities.Status;

public class BillAdminViewDto {

	Long id;

	Long customUserId;

	String firstname;

	String lastname;

	int num;

	String street;

	String town;

	String postal;

	String country;

	String email;

	LocalDate billDate;

	Double totalPriceHT;

	Double totalPriceTTC;

	Double totalWeight;

	int totalItem;

	Status status;

	public BillAdminViewDto(Long id, Long customUserId, String firstname, String lastname, int num, String street,
			String town, String postal, String country, String email, LocalDate billDate, Double totalPriceHT,
			Double totalPriceTTC, Double totalWeight, int totalItem, Status status) {
		super();
		this.id = id;
		this.customUserId = customUserId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.num = num;
		this.street = street;
		this.town = town;
		this.postal = postal;
		this.country = country;
		this.email = email;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public Double getTotalPriceHT() {
		return totalPriceHT;
	}

	public void setTotalPriceHT(Double totalPriceHT) {
		this.totalPriceHT = totalPriceHT;
	}

	public Double getTotalPriceTTC() {
		return totalPriceTTC;
	}

	public void setTotalPriceTTC(Double totalPriceTTC) {
		this.totalPriceTTC = totalPriceTTC;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
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
