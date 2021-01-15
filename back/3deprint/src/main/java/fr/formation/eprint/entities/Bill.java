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
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "orders", uniqueConstraints = {
		@UniqueConstraint(name = "orders_reference_UNIQUE", columnNames = { "orderRef" }) }, indexes = {
				@Index(name = "orders_customer_id_IDX", columnList = "customer_id")})
public class Bill extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "orders_customer_id_FK"), nullable = false)
	private CustomUser customUser;

	@Column(name = "orderRef", columnDefinition = "CHAR(36)", nullable = false)
	private UUID orderRef;

	@Column(name = "date", nullable = false)
	private LocalDate orderDate;

	@OneToMany
	@Column(name = "ordereds", nullable = false)
	private List<Ordered> ordereds;

	@Column(name = "totalPrice", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
	private double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
	private Status status;

	protected Bill() {
		// Default no-arg constructor for libs
	}

	public Bill(CustomUser customUser, UUID orderRef, LocalDate orderDate, List<Ordered> ordereds, double totalPrice,
			Status status) {
		super();
		this.customUser = customUser;
		this.orderRef = orderRef;
		this.orderDate = orderDate;
		this.ordereds = ordereds;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
