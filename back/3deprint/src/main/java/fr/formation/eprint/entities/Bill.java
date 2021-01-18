package fr.formation.eprint.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "bills", uniqueConstraints = {
		@UniqueConstraint(name = "bills_reference_UNIQUE", columnNames = { "billRef" }) }, indexes = {
				@Index(name = "bills_customUser_id_IDX", columnList = "customUser_id")})
public class Bill extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "customUser_id", foreignKey = @ForeignKey(name = "Bills_customUser_id_FK"), nullable = false)
	private CustomUser customUser;

	@Column(name = "billRef", columnDefinition = "CHAR(36)", nullable = false)
	private UUID billRef;

	@Column(name = "date", nullable = false)
	private LocalDate orderDate;

	@OneToMany
	@Column(name = "orders", nullable = false)
	private List<Order> orders;

	@Column(name = "totalPrice", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
	private Status status;

	protected Bill() {
		// Default no-arg constructor for libs
	}

	public Bill(CustomUser customUser, UUID billRef, LocalDate orderDate, List<Order> orders, double totalPrice,
			Status status) {
		super();
		this.customUser = customUser;
		this.billRef = billRef;
		this.orderDate = orderDate;
		this.orders = orders;
		this.totalPrice = totalPrice;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
