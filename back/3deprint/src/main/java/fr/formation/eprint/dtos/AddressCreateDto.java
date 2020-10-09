package fr.formation.eprint.dtos;

import javax.validation.constraints.Max;

public class AddressCreateDto {
    @Max(4)
    private int num;
    @Max(40)
    private String street;
    @Max(40)
    private String town;
    @Max(30)
    private String country;

    public AddressCreateDto() {
	super();
	// TODO Auto-generated constructor stub
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
