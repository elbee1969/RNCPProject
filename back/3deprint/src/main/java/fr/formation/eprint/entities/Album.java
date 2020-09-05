package fr.formation.eprint.entities;

import javax.persistence.Column;

public class Album extends AbstractEntity {
	
	@Column(name= "userid")
	private String userId;
	
	/**
	 * 
	 */
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public Album(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Album [userId=" + userId + ", getUserId()=" + getUserId() + ", getId()=" + getId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
