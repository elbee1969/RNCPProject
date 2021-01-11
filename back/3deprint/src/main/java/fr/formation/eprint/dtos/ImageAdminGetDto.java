package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.CustomUser;

public class ImageAdminGetDto {
	private Long id;
    private String name;
    private String ownerName;
    private String type;
    private String url;
    private String status;
    private int quantity; 
    private Long userId;
//    private String type;
//    private byte[] data;
//    private CustomUser customUser;

   
    public ImageAdminGetDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

 
//    public String getType() {
//	return type;
//    }
//
//    public void setType(String type) {
//	this.type = type;
//    }
//
// 
//    public byte[] getData() {
//		return data;
//	}
//
//
//	public void setData(byte[] data) {
//		this.data = data;
//	}


//	public CustomUser getCustomUser() {
//		return customUser;
//	}
//
//
//	public void setCustomUser(CustomUser customUser) {
//		this.customUser = customUser;
//	}


	
}