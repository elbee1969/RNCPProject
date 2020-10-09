package fr.formation.eprint.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_image_id"))
    private List<Image> images;

    public Album() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Album(Long id) {
	super(id);
	// TODO Auto-generated constructor stub
    }

    public List<Image> getImages() {
	return images;
    }

    public void setImages(List<Image> images) {
	this.images = images;
    }

}
