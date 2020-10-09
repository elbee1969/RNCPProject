package fr.formation.eprint.dtos;

import java.util.List;

import fr.formation.eprint.entities.Image;

public class AlbumCreateDto {

    private List<Image> images;

    public AlbumCreateDto() {
	super();
	// TODO Auto-generated constructor stub
    }

    public List<Image> getImages() {
	return images;
    }

    public void setImages(List<Image> images) {
	this.images = images;
    }

}
