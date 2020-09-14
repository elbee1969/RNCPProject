package fr.formation.eprint.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fileDB")
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name= "id")
  private String id;
  
  @Column(name= "name")
  private String name;
  
  @Column(name= "type")
  private String type;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name= "data")
  private byte[] data;
  


public FileDB() {

}



public FileDB( byte[] data,String name, String type) {
	this.name = name;
	this.type = type;
	this.data = data;
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


}