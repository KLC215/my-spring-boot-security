package com.my.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

	private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/orders")
	public Callable<String> fetchOrders() throws InterruptedException {
		logger.info("fetchOrders: main thread start");

		// Simulate order process
		Callable<String> result = () -> {
			logger.info("fetchOrders: sub thread start");
			Thread.sleep(1000);
			logger.info("fetchOrders: sub thread end");
			return "success";
		};

		logger.info("fetchOrders: main thread end");

		return result;
	}

	@RequestMapping("/orders2")
	public DeferredResult<String> fetchOrders2() throws InterruptedException {
		logger.info("fetchOrders2: main thread start");

		// Simulate order process
		String orderNo = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNo);

		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getResultMap().put(orderNo, result);

		logger.info("fetchOrders2: main thread end");

		return result;
	}

}
