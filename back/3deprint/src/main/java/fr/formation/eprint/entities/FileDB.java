package fr.formation.eprint.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "file")
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  private String type;

  @Lob
  private byte[] data;
  
//  @ManyToOne
//  @JoinColumn(name = "album_id", referencedColumnName = "id",
//          nullable = false,
//          foreignKey = @ForeignKey(name = "FK_file_album"))
//  private Album album;

public FileDB() {
	super();
	// TODO Auto-generated constructor stub
}

public FileDB(String id, String name, String type, byte[] data) {
	super();
	this.id = id;
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
//public void setAlbum(Album album) {
//	this.album = album;
//}

@Override
public String toString() {
	return "FileDB [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data) + ", getId()=" + getId() + ", getName()=" + getName() + ", getType()=" + getType() + ", getData()="
			+ Arrays.toString(getData()) + ", getClass()=" + getClass() + ", hashCode()="
			+ hashCode() + ", toString()=" + super.toString() + "]";
} 

  

}