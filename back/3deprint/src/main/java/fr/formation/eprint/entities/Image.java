package fr.formation.eprint.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image extends AbstractEntity {

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "type", length = 40, nullable = false)
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", nullable = false)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "custom_user_id")
    private CustomUser customUser;

    public Image(byte[] data, String name, String type, CustomUser customUser) {
	this.name = name;
	this.type = type;
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

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
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