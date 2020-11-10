package fr.formation.eprint.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item  extends AbstractEntity {
	
	@Column(name = "name", length = 255, nullable = false)
    private String name;
	
	@Column(name = "quantity", length = 3, nullable = false)
    private int quantity;
	
    @Column(name = "price",columnDefinition = "DECIMAL(3, 1) UNSIGNED", nullable = false)
    private float price;
    
    @Column(name = "totalPrice", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
}
