package fr.formation.eprint.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "orders",
	uniqueConstraints = {
		@UniqueConstraint(name = "orders_reference_UNIQUE",
			columnNames = { "orderRef" }) },
	indexes = {
		@Index(name = "orders_customer_id_IDX",
			columnList = "customer_id"),
		@Index(name = "orders_address_id_IDX",
			columnList = "address_id") })
public class Order extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "orders_customer_id_FK"),
	    nullable = false)
    private CustomUser customUser;

    @ManyToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "orders_address_id_FK"),
	    nullable = false)
    private Address address;
    
    @Column(name="orderRef", columnDefinition = "CHAR(36)", nullable = false)
    private UUID orderRef;

    @Column(name = "date", nullable = false)
    private LocalDate orderDate;
    
    @Column(name = "state", length = 1 , nullable = false)
    private String state;

    @OneToMany
    @Column(name = "items", nullable = false)
    private List<Item> items;

    @Column(name = "totalPrice", columnDefinition = "DECIMAL(10, 2) UNSIGNED", nullable = false)
    private double totalPrice;

    protected Order() {
	// Default no-arg constructor for libs
    }

}
