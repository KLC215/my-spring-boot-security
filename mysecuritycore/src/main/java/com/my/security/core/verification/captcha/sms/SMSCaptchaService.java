package com.my.security.core.verification.captcha.sms;

public interface SMSCaptchaService {

	void sendSMS(String mobile, String message);

}
