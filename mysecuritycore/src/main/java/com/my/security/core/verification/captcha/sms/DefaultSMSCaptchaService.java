package com.my.security.core.verification.captcha.sms;

public class DefaultSMSCaptchaService implements SMSCaptchaService {
	@Override
	public void sendSMS(String mobile, String message) {
		System.out.println("DefaultSMSCaptchaService->sendSMS() Mobile: " + mobile);
		System.out.println("DefaultSMSCaptchaService->sendSMS() Message: " + message);
	}
}
