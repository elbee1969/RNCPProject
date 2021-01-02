package fr.formation.eprint.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "image3d")
public class Image3D extends AbstractEntity {
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "url", length = 40, nullable = false)
	private String url;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customUser_id")
	private CustomUser customUser;

	@OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<Item>();

	public Image3D(String name, String url, CustomUser customUser) {
		this.name = name;
		this.url = url;
		this.customUser = customUser;
	    }

	public Image3D() {
		super();
		// TODO Auto-generated constructor stub
	    }

	public Image3D(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}
}
