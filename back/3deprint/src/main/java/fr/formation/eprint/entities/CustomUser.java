package fr.formation.eprint.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

import fr.formation.eprint.dtos.AlbumCreateDto;
import fr.formation.eprint.dtos.UserCreateDto;

@Entity
@Table(	name = "customuser", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
//			@UniqueConstraint(name = "user_album_id_UQ", columnNames = {
//			"album_id" }) }, indexes = {
//				@Index(name = "user_album_id_IDX", columnList = "album_id")
		})
public class CustomUser extends AbstractEntity {


	@Column(name= "username",length = 40, nullable = false)
	private String username;
	
	@Email
	@Column(name= "email",length = 100, nullable = false)
	private String email;

	@Column(name= "password")
	private String password;

	@Column(name= "firstname",length = 40, nullable = false)
	private String firstname;
	
	@Column(name = "lastname",length = 40, nullable = false)
	private String lastname;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "roles" )
	private Set<Role> roles;
	
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
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_customuser", nullable = false)
    @Column(name = "albums")
    private List<Album> albums;
    


	public CustomUser(String username, @Email String email, String password, String firstname, String lastname,
			Set<Role> roles, boolean enabled, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, AlbumCreateDto albumCreateDto) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.albums = (List<Album>) albumCreateDto;
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
    public CustomUser(String password, String username, Set<Role> roles,
	    boolean enabled) {
	this.password = password;
	this.username = username;
	this.roles = roles;
	this.enabled = enabled;
    }


	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public CustomUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomUser(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
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



	
}