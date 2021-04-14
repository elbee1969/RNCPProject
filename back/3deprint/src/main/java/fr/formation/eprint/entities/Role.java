package fr.formation.eprint.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.formation.eprint.utility.BooleanConverter;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

	@Column(name = "code", length = 20)
	private String code;

	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean defaultRole = false;

	protected Role() {
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

}