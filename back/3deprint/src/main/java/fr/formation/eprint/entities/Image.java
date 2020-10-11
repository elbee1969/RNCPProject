package fr.formation.eprint.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
//    @Column(name = "album_id", nullable = false)
    private Album album;

    public Image() {

    }

    public Image(byte[] data, String name, String type, Album album) {
	this.name = name;
	this.type = type;
	this.data = data;
	this.album = album;
    }

    public Image(String name2, String fileDownloadUri, String type2, int length, Album album2) {
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

    public Album getAlbum() {
	return album;
    }

    public void setAlbum(Album album) {
	this.album = album;
    }

}