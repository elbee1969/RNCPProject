package fr.formation.eprint.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bills", uniqueConstraints = {
		@UniqueConstraint(name = "bills_reference_UNIQUE", columnNames = { "id" }) }, indexes = {
				@Index(name = "bills_customUser_id_IDX", columnList = "customUser_id") })
public class Bill extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "customUser_id", foreignKey = @ForeignKey(name = "Bills_customUser_id_FK"), nullable = false)
	private CustomUser customUser;

	@Column(name = "date", nullable = false)
	private LocalDate billDate;

	@Column(name = "totalPriceHT", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPriceHT;

	@Column(name = "totalPriceTTC", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPriceTTC;

	@Column(name = "totalWeight", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalWeight;

	@Column(name = "totalItem", nullable = false)
	private int totalItem;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A','O')", length = 1, nullable = false)
	private Status status;

	public Bill(CustomUser customUser, LocalDate billDate, double totalPriceHT, double totalPriceTTC,
			double totalWeight, int totalItem, Status status) {

		this.customUser = customUser;
		this.billDate = billDate;
		this.totalPriceHT = totalPriceHT;
		this.totalPriceTTC = totalPriceTTC;
		this.totalWeight = totalWeight;
		this.totalItem = totalItem;
		this.status = status;
	}

	public Bill() {
		// TODO Auto-generated constructor stub
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
