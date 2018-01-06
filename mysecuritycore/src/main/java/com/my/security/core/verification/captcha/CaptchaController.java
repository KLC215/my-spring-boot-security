package com.my.security.core.verification.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class CaptchaController {

	static final String SESSION_KEY = "SESSION_KEY_IMAGE_CAPTCHA";
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private CaptchaGenerator imageCaptchaGenerator;

	@GetMapping("/captcha/image")
	public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ImageCaptcha imageCaptcha = imageCaptchaGenerator.createImageCaptcha(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCaptcha);
		ImageIO.write(imageCaptcha.getImage(), "JPEG", response.getOutputStream());
	}

}
