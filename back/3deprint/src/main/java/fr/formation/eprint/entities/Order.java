package fr.formation.eprint.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")

public class Order extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "image_id", nullable = false)
	private Image image;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "quantity", length = 3, nullable = false)
	private int quantity;

	@Column(name = "weight", columnDefinition = "DECIMAL(7, 3) UNSIGNED")
	private float weight;

	@Column(name = "price", columnDefinition = "DECIMAL(7, 2) UNSIGNED")
	private float price;

	@Column(name = "totalPrice", columnDefinition = "DECIMAL(7, 2) UNSIGNED")
	private double totalPrice;

	@Column(name = "totalWeight", columnDefinition = "DECIMAL(7, 2) UNSIGNED")
	private double totalWeight;

	@Column(name = "timeToPrint", length = 40, nullable = false)
	private String timeToPrint;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customUser_id", nullable = false)
	private CustomUser customUser;

	public Order() {

	}

	public Order(Image image, String name, int quantity, float weight, float price, double totalPrice,
			double totalWeight, String timeToPrint, Status status, CustomUser customUser) {
		this.image = image;
		this.name = name;
		this.quantity = quantity;
		this.weight = weight;
		this.price = price;
		this.totalPrice = totalPrice;
		this.totalWeight = totalWeight;
		this.timeToPrint = timeToPrint;
		this.status = status;
		this.customUser = customUser;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getTimeToPrint() {
		return timeToPrint;
	}

	public void setTimeToPrint(String timeToPrint) {
		this.timeToPrint = timeToPrint;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

}
