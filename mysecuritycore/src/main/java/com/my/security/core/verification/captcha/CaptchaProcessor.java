package com.my.security.core.verification.captcha;

import org.springframework.web.context.request.ServletWebRequest;

public interface CaptchaProcessor {

	/**
	 * The prefix of session key
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CAPTCHA_";

	/**
	 * Create captcha
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

}
