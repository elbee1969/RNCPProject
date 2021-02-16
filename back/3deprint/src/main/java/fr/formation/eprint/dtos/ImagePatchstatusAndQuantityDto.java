package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Status;

public class ImagePatchstatusAndQuantityDto {

	private Status status;
	private int quantity;

	public ImagePatchstatusAndQuantityDto() {

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
