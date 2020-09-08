package fr.formation.eprint.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "album")
public class Album extends AbstractEntity {
	
    @OneToOne
    @MapsId
    private CustomUser customuser;
	
	
	

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public CustomUser getCustomuser() {
		return customuser;
	}

	public void setCustomuser(CustomUser customuser) {
		this.customuser = customuser;
	}

	
	
		
	
	
	

	
	
}
