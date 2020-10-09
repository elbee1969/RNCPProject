package fr.formation.eprint.entities;

import javax.persistence.Column;

public class adresse  extends AbstractEntity {
	
    @Column(name= "num", length = 4)
	private int num;
    @Column(name= "street", length = 40)
	private String street;
    @Column(name= "town", length = 40)
	private String town;
    @Column(name= "country", length = 30)
	private String country;
}
