package fr.formation.eprint.entities;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(	name = "user", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
//			@UniqueConstraint(name = "user_album_id_UQ", columnNames = {
//			"album_id" }) }, indexes = {
//				@Index(name = "user_album_id_IDX", columnList = "album_id")
		})
public class User extends AbstractEntity {


	@Column(name= "username",length = 40, nullable = false)
	private String username;
	
	@Email
	@Column(name= "email",length = 100, nullable = false)
	private String email;

	@Column(name= "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	
	@OneToOne(mappedBy = "user")
    private Album album;
	
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

    @Column(length = 40, nullable = false)
    private String firstname;

    @Column(length = 40, nullable = false)
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
	
	
	
	

	

	public User(String username, @Email String email, String password, Set<Role> roles, Album album, boolean enabled,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, String firstname,
			String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.album = album;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
				+ ", album=" + album + ", enabled=" + enabled + ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}


	

	
	
	

	
}