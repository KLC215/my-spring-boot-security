package com.my.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

	private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

	@RequestMapping("/orders")
	public Callable<String> fetchOrders() throws InterruptedException {
		logger.info("fetchOrders: main thread start");

		// Simulate order process
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("fetchOrders: sub thread start");
				Thread.sleep(1000);
				logger.info("fetchOrders: sub thread end");
				return "success";
			}
		};

		logger.info("fetchOrders: main thread end");

		return result;
	}

}
