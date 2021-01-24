package fr.formation.eprint.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.formation.eprint.entities.Address;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;

public class OrderCreateDto {

private Image image;
	
    private String name;
	
    private int quantity;
	
    private float weight;
    
    private float price;
    
    private double totalPrice;

    private String timeToPrint;
    
    private Status status;
    
    private long customUserId;

	public OrderCreateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderCreateDto(String name, int quantity, float weight, float price, double totalPrice, Image image, Status status,  CustomUser customUser) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.weight = weight;
		this.price = price;
		this.totalPrice = totalPrice;
		this.image = image;
		this.status = status;
		this.customUserId = customUserId;
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

	public long getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(long customUserId) {
		this.customUserId = customUserId;
	}

	

	
	
}
