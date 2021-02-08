package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Status;

public class OrderPatchDto {

	private Status status;

	public OrderPatchDto() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
