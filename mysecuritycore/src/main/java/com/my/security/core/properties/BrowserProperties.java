package com.my.security.core.properties;

public class BrowserProperties {

	private String loginPage = "/my-login.html";

	private LoginType loginType = LoginType.JSON;

	private int rememberMeExpirationTime = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeExpirationTime() {
		return rememberMeExpirationTime;
	}

	public void setRememberMeExpirationTime(int rememberMeExpirationTime) {
		this.rememberMeExpirationTime = rememberMeExpirationTime;
	}
}
