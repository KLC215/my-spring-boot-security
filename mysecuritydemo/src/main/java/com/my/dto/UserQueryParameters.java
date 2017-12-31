package com.my.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryParameters {

	private String username;

	@ApiModelProperty(value = "Age of user")
	private int age;

	@ApiModelProperty(value = "Maximum age of user")
	private int maxAge;

	private String somethingYouWantTo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getSomethingYouWantTo() {
		return somethingYouWantTo;
	}

	public void setSomethingYouWantTo(String somethingYouWantTo) {
		this.somethingYouWantTo = somethingYouWantTo;
	}
}
