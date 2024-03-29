package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Status;

public class OrderDto {

	private Long id;

	private Long imageId;

	private String name;

	private int quantity;

	private float weight;

	private float price;

	private double totalPrice;

	private double totalWeight;

	private String timeToPrint;

	private Status status;

	private Long customUserId;

	public OrderDto() {

	}

	public OrderDto(Long id, Long imageId, String name, int quantity, float weight, float price, double totalPrice,
			double totalWeight, String timeToPrint, Status status, Long customUserId) {

		this.id = id;
		this.imageId = imageId;
		this.name = name;
		this.quantity = quantity;
		this.weight = weight;
		this.price = price;
		this.totalPrice = totalPrice;
		this.totalWeight = totalWeight;
		this.timeToPrint = timeToPrint;
		this.status = status;
		this.customUserId = customUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

	public Long getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(Long customUserId) {
		this.customUserId = customUserId;
	}

}
