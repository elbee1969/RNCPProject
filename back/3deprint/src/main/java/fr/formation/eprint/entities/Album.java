package fr.formation.eprint.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.formation.eprint.dtos.AlbumCreateViewDto;


@Entity
@Table(name = "album")
public class Album extends AbstractEntity {
	
	
	
	@OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_album", nullable = false)
    @Column(name = "FileDBs")
    private List<FileDB> FileDBs;
	

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Album(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	public List<FileDB> getFileDBs() {
		return FileDBs;
	}


	public void setFileDBs(List<FileDB> fileDBs) {
		FileDBs = fileDBs;
	}


}
