package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.CustomUser;

public class ImageGetDto {
    private String name;
    private String type;
    private byte[] data;
    private CustomUser customUser;

    public ImageGetDto(String name,String type, byte[] data,CustomUser customUser) {
	this.name = name;
	this.type = type;
	this.data = data;
	this.customUser = customUser;
    }


    public ImageGetDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

 
    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

 
    public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	public CustomUser getCustomUser() {
		return customUser;
	}


	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}


	
}