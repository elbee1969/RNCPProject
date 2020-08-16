package fr.formation.eprint.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  private String type;

  @Lob
  private byte[] data;
  
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id",
          nullable = false,
          foreignKey = @ForeignKey(name = "FK_file_user"))
  private User user; 

 
  public FileDB(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;

  }



public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public void setId(String id) {
	this.id = id;
}

public String getId() {
    return id;
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