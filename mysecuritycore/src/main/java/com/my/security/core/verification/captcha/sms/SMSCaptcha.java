package com.my.security.core.verification.captcha.sms;

import com.my.security.core.verification.captcha.Captcha;

import java.time.LocalDateTime;

public class SMSCaptcha extends Captcha {
	public SMSCaptcha(String code, LocalDateTime expireTime) {
		super(code, expireTime);
	}

	public SMSCaptcha(String code, int expireIn) {
		super(code, expireIn);
	}
}
