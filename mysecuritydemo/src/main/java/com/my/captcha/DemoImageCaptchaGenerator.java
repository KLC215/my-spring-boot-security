package com.my.captcha;

import com.my.security.core.verification.captcha.CaptchaGenerator;
import com.my.security.core.verification.captcha.ImageCaptcha;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("imageCaptchaGenerator")
public class DemoImageCaptchaGenerator implements CaptchaGenerator{
	@Override
	public ImageCaptcha createImageCaptcha(ServletWebRequest request) {
		System.out.println("DemoImageCaptchaGenerator->createImageCaptcha() ----: ");
		return null;
	}
}
