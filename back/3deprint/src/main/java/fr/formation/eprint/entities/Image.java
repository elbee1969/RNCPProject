package fr.formation.eprint.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "images")
public class Image extends AbstractEntity {

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "ownerName", length = 40, nullable = false)
	private String ownerName;

	@Column(name = "type", length = 40, nullable = false)
	private String type;

	@Column(name = "url", length = 255, nullable = false)
	private String url;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('I','C','V','A','O')", length = 1, nullable = false)
	private Status status;

	@Column(name = "quantity", length = 3)
	private int quantity;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customUser_id")
	private CustomUser customUser;

	public Image(String name, String ownerName, String type, String url, Status status, int quantity, Date date,
			CustomUser customUser) {
		this.name = name;
		this.ownerName = ownerName;
		this.type = type;
		this.url = url;
		this.status = status;
		this.quantity = quantity;
		this.date = date;
		this.customUser = customUser;
	}

	@PrePersist // before database's insertion
	public void prePresist() {
		date = new Date();
	}

	public Image() {

	}

	public Image(Long id) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Status getStatus() {
		return status;
	}

	public Status setStatus(Status status) {
		return status;
	}

	public int getQuantity() {
		return quantity;
	}

	public int setQuantity(int number) {
		return number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

}