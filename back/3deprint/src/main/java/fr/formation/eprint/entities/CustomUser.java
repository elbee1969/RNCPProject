package fr.formation.eprint.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "customUser", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class CustomUser extends AbstractEntity {

	@Column(name = "username", length = 40, nullable = false)
	private String username;

	@Email
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "password", length = 255, nullable = false)
	private String password;

	@Column(name = "firstname", length = 40, nullable = false)
	private String firstname;

	@Column(name = "lastname", length = 40, nullable = false)
	private String lastname;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "roles")
	private Set<Role> roles;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_custom_user_address_id"))
	private Address address;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "enabled", length = 1, nullable = false)
	private boolean enabled;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "accountNonExpired", length = 1, nullable = false)
	private boolean accountNonExpired;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "accountNonLocked", length = 1, nullable = false)
	private boolean accountNonLocked;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "credentialsNonExpired", length = 1, nullable = false)
	private boolean credentialsNonExpired;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customUser", cascade = CascadeType.REFRESH)
	private List<Image> images = new ArrayList<Image>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customUser", cascade = CascadeType.REFRESH)
	private List<Order> orders = new ArrayList<Order>();

	public CustomUser(String username, @Email String email, String password, String firstname, String lastname,
			Set<Role> roles, Address address, boolean enabled, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
		this.address = address;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;

	}

	public CustomUser(String username, @Email String email, String password, String firstname, String lastname,
			Set<Role> roles, Address address, boolean enabled, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, List<Image> images) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
		this.address = address;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.images = images;
	}

	public CustomUser(String username, String password, String firstname, String lastname, Set<Role> roles,
			boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * Creates a new enabled user.
	 *
	 * @param password an encrypted password
	 * @param username a unique username
	 * @param roles    some roles
	 */
	public CustomUser(String password, String username, Set<Role> roles) {
		this(password, username, roles, true);
	}

	/**
	 * Creates a new user.
	 *
	 * @param password an encrypted password
	 * @param username a unique username
	 * @param roles    some roles
	 * @param enabled  {@code true} if enabled; {@code false} otherwise
	 */
	public CustomUser(String password, String username, Set<Role> roles, boolean enabled) {
		this.password = password;
		this.username = username;
		this.roles = roles;
		this.enabled = enabled;
	}

	protected CustomUser() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setAddress(Long id, int num, String street, String town, String country) {
		// TODO Auto-generated method stub
		this.setAddress(id, num, street, town, country);
	}

}