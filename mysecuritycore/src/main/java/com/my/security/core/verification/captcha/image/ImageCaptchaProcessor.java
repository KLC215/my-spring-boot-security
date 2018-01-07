package com.my.security.core.verification.captcha.image;

import com.my.security.core.verification.captcha.AbstractCaptchaProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("imageCaptchaProcessor")
public class ImageCaptchaProcessor extends AbstractCaptchaProcessor<ImageCaptcha> {
	@Override
	protected void send(ServletWebRequest request, ImageCaptcha captcha) throws Exception {
		ImageIO.write(captcha.getImage(), "JPEG", request.getResponse().getOutputStream());
	}
}
