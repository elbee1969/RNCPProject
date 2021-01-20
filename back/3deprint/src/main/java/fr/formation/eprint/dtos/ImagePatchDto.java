package fr.formation.eprint.dtos;

import java.util.Date;

import javax.persistence.PrePersist;

import fr.formation.eprint.entities.Status;

public class ImagePatchDto {

	private Status status;
	private int quantity;
//	private Date date;

	// private byte[] data;
	// private CustomUser customUser;
	public ImagePatchDto() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*
	 * public Date getDate() { return date; }
	 * 
	 * public void setDate(Date date) { this.date = date; }
	 */

}
