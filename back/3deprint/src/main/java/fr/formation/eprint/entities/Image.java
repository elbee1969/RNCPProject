package fr.formation.eprint.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image extends AbstractEntity {

    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @Column(name="ownerName", length= 40, nullable = false)
    private String ownerName;

    @Column(name = "type", length = 40, nullable = false)
    private String type;
    
    @Column(name = "url", length = 255, nullable = false)
	private String url;
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('I','C','V','A')", length = 1, nullable = false)
    private Status status;
    
    @Column(name = "quantity", length = 3)
    private int quantity;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", nullable = false)
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customUser_id")
    private CustomUser customUser;
    
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<Item>();

    public Image(byte[] data, String name, String ownerName, String type, String url, Status status, int number, CustomUser customUser) {
	this.name = name;
	this.ownerName = ownerName;
	this.type = type;
	this.url = url;
	this.status = status;
	this.quantity = number;
	this.data = data;
	this.customUser = customUser;
    }

    public Image() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Image(Long id) {
	super(id);
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Status getStatus() {
		return status;
	}

	public Status setStatus(Status status) {
		return status;
	}

	public int getQuantity() {
		return quantity;
	}

	public int setQuantity(int number) {
		return number;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public byte[] getData() {
	return data;
    }

    public void setData(byte[] data) {
	this.data = data;
    }

    public CustomUser getCustomUser() {
	return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
	this.customUser = customUser;
    }

}