package fr.formation.eprint.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bills", uniqueConstraints = {
		@UniqueConstraint(name = "bills_reference_UNIQUE", columnNames = { "billRef" }) }, indexes = {
				@Index(name = "bills_customUser_id_IDX", columnList = "customUser_id") })
public class Bill extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "customUser_id", foreignKey = @ForeignKey(name = "Bills_customUser_id_FK"), nullable = false)
	private CustomUser customUser;

	@Column(name = "billRef", columnDefinition = "CHAR(36)", nullable = false)
	private UUID billRef;

	@Column(name = "date", nullable = false)
	private LocalDate orderDate;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Order.class, mappedBy = "bill")
	private List<Order> orders;

	@Column(name = "totalPriceHT", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPriceHT;

	@Column(name = "totalPriceTTC", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPriceTTC;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
	private Status status;

	public Bill() {
		// Default no-arg constructor for libs
	}

	public Bill(CustomUser customUser, UUID billRef, LocalDate orderDate, List<Order> orders, double totalPriceHT,
			double totalPriceTTC, Status status) {
		super();
		this.customUser = customUser;
		this.billRef = billRef;
		this.orderDate = orderDate;
		this.orders = orders;
		this.totalPriceHT = totalPriceHT;
		this.totalPriceTTC = totalPriceTTC;
		this.status = status;
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

	public List<Order> getItems() {
		return orders;
	}

	public void setItems(List<Order> orders) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
