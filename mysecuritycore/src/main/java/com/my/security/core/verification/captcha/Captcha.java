package com.my.security.core.verification.captcha;

import java.time.LocalDateTime;

public class Captcha {

	private String code;
	private LocalDateTime expireTime;

	public Captcha(String code, LocalDateTime expireTime) {

		this.code = code;
		this.expireTime = expireTime;
	}

	public Captcha(String code, int expireIn) {

		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
