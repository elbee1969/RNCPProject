package fr.formation.eprint.entities;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(	name = "user", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User extends AbstractEntity {


	@Column(name= "username")
	private String username;
	
	@Email
	@Column(name= "email")
	private String email;

	@Column(name= "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean enabled;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonExpired;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonLocked;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean credentialsNonExpired;

    @Column(length = 256, nullable = false)
    private String firstname;

    @Column(length = 256, nullable = false)
    private String lastname;

	
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Creates a new enabled user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     */
    public User(String password, String username, Set<Role> roles) {
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
    public User(String password, String username, Set<Role> roles,
	    boolean enabled) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
    }

	/**
	 * @param id
	 */
	public User(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param roles
	 * @param enabled
	 * @param accountNonExpired
	 * @param accountNonLocked
	 * @param credentialsNonExpired
	 * @param firstname
	 * @param lastname
	 */
	public User(String username, @Email String email, String password, Set<Role> roles, boolean enabled,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, String firstname,
			String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.firstname = firstname;
		this.lastname = lastname;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", enabled=" + enabled + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getRoles()=" + getRoles() + ", isEnabled()=" + isEnabled()
				+ ", isAccountNonExpired()=" + isAccountNonExpired() + ", isAccountNonLocked()=" + isAccountNonLocked()
				+ ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", getFirstname()=" + getFirstname()
				+ ", getLastname()=" + getLastname() + ", getId()=" + getId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	

	
	
	

	
}