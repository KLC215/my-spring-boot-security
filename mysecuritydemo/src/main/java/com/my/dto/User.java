package com.my.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class User {

	// Response all fields without private details
	public interface UserGeneralJsonView {}

	// Response all fields with private details
	public interface UserDetailJsonView extends UserGeneralJsonView {}

	private Integer id;
	private String username;

	@NotBlank
	private String password;
	private Date dob;

	@JsonView(UserGeneralJsonView.class)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonView(UserGeneralJsonView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// JUST FOR DEMO !!!
	// DON'T DO THIS IN REAL PROJECT !!!
	// MUST NOT SHOW PASSWORD TO PUBLIC !!!
	@JsonView(UserDetailJsonView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserGeneralJsonView.class)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
