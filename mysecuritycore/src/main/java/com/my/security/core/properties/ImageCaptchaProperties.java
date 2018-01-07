package com.my.security.core.properties;

public class ImageCaptchaProperties extends SMSCaptchaProperties {

	private int width = 67;
	private int height = 23;

	public ImageCaptchaProperties() {
		setLength(4);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
