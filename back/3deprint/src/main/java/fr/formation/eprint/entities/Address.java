package fr.formation.eprint.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends AbstractEntity {

    @Column(name = "num", length = 4)
    private int num;

    @Column(name = "street", length = 40)
    private String street;

    @Column(name = "town", length = 40)
    private String town;

    @Column(name = "country", length = 30)
    private String country;

    public Address() {
	super();
    }

    public Address(int num, String street, String town, String country) {
		super();
		this.num = num;
		this.street = street;
		this.town = town;
		this.country = country;
	}

	public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getTown() {
	return town;
    }

    public void setTown(String town) {
	this.town = town;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

}
