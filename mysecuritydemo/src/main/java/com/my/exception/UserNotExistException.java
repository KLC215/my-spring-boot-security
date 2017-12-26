package com.my.exception;

public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = -4989688039675698508L;

	private Integer id;

	public UserNotExistException(Integer id) {
		super("UserId: " + id + " is not exist");
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
