package com.my.security.core.verification.captcha;

import org.springframework.web.context.request.ServletWebRequest;

public interface CaptchaGenerator {

	Captcha generate(ServletWebRequest request);

}
