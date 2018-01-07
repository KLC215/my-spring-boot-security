package com.my.security.core.verification.captcha.image;

import com.my.security.core.verification.captcha.Captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCaptcha extends Captcha {

	private BufferedImage image;

	public ImageCaptcha(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}

	public ImageCaptcha(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
