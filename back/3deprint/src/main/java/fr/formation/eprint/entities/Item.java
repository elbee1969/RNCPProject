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
@Table(name = "item")
public class Item  extends AbstractEntity {
	
	@Column(name = "name", length = 255, nullable = false)
    private String name;
	
	@Column(name = "quantity", length = 3, nullable = false)
    private int quantity;
	
    @Column(name = "weight",columnDefinition = "DECIMAL(7, 3) UNSIGNED", nullable = false)
    private float weight;
    
    @Column(name = "price",columnDefinition = "DECIMAL(7, 2) UNSIGNED", nullable = false)
    private float price;
    
    @Column(name = "totalPrice", columnDefinition = "DECIMAL(7, 2) UNSIGNED", nullable = false)
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
    private Status status;
    
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Item(String name, int quantity, float weight, float price, double totalPrice, Image image, Status status) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.weight = weight;
		this.price = price;
		this.totalPrice = totalPrice;
		this.image = image;
		this.status = status;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
}
