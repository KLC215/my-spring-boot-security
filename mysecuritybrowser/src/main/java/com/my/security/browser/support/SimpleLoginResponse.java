package com.my.security.browser.support;

public class SimpleLoginResponse {

	private Object content;

	public SimpleLoginResponse(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
