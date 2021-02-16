package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.Status;

public class ImagePatchStatusDto {

	private Status status;



	public ImagePatchStatusDto() {

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
