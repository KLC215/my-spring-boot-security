package com.my.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeferredResultHolder {

	/**
	 * Simulate orders
	 * String	Order no.
	 * DeferredResult<String>	Order result
	 */
	private Map<String, DeferredResult<String>> resultMap = new HashMap<>();

	public Map<String, DeferredResult<String>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, DeferredResult<String>> resultMap) {
		this.resultMap = resultMap;
	}
}
