package fr.formation.eprint.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;



public class AddressCreateDto {

	@NotNull
    private int num;
	
    @Length(max = 40)
    private String street;
    @Length(max = 40)
    private String town;
    @Length(max = 30)
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
