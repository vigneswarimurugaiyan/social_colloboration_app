package com.product.model;

import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class User {
	@Id
	private String Id;
	@Column
	private String Title;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private Date dateofbirth;
	@Column
	private String email;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
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
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
