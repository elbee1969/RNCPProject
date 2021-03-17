package fr.formation.eprint.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

	@Column(name = "code", length = 20)
	private String code;

	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean defaultRole = false;

	protected Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public Role(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Role(String code) {
		setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	@Override
	public String toString() {
		return "Role [code=" + code + ", defaultRole=" + defaultRole + ", getId()=" + getId() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}