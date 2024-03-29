package fr.formation.eprint.dtos;

import java.util.Date;

public class ImageAdminGetDto {
	private Long id;
	private String name;
	private String ownerName;
	private String type;
	private String url;
	private String status;
	private int quantity;
	private Date date;
	private Long userId;

	public ImageAdminGetDto() {

	}

	public ImageAdminGetDto(Long id, String name, String ownerName, String type, String url, String status,
			int quantity, Date date, Long userId) {
		this.id = id;
		this.name = name;
		this.ownerName = ownerName;
		this.type = type;
		this.url = url;
		this.status = status;
		this.quantity = quantity;
		this.date = date;
		this.userId = userId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}