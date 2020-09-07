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
	
//	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, 
//	        mappedBy = "album")
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	
	

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
		
	
	
	

	
	
}
