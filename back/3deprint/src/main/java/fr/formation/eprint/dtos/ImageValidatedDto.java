package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Status;

public class ImageValidatedDto {

	private Status status;



	public ImageValidatedDto() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
