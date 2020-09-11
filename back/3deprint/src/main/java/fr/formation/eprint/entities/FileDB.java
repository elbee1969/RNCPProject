package fr.formation.eprint.entities;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fileDB")

//, indexes = {
//		@Index(name = "fileDB_album_id_IDX", columnList = "album_id") }
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  
  @Column(name= "name")
  private String name;
  
  @Column(name= "type")
  private String type;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name= "data")
  private byte[] data;
  
//  @ManyToOne
//  @JoinColumn(name="album_id", foreignKey = @ForeignKey(name="fileDB_album_id_FK"), nullable = false)
//  private Album album;

public FileDB() {
	// TODO Auto-generated constructor stub
}



public FileDB( byte[] data,String name, String type) {
	this.name = name;
	this.type = type;
	this.data = data;
//	this.album = album;
}




public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
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



//public Album getAlbum() {
//	return album;
//}
//
//
//
//public void setAlbum(Album album) {
//	this.album = album;
//}




}