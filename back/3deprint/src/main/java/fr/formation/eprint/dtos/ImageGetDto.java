package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.CustomUser;

public class ImageGetDto {
	private Long id;
    private String name;
    private String type;
    private String url;
    private Long userId;
//    private String type;
//    private byte[] data;
//    private CustomUser customUser;

   
    public ImageGetDto() {
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