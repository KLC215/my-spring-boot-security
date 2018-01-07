package com.my.captcha;

import com.my.security.core.verification.captcha.Captcha;
import com.my.security.core.verification.captcha.CaptchaGenerator;
import org.springframework.web.context.request.ServletWebRequest;

//@Component("imageCaptchaGenerator")
public class DemoImageCaptchaGenerator implements CaptchaGenerator{
	@Override
	public Captcha generate(ServletWebRequest request) {
		System.out.println("DemoImageCaptchaGenerator->createImageCaptcha() ----: ");
		return null;
	}
}
