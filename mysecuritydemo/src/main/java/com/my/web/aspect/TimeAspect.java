package com.my.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class TimeAspect {

	// Which method can be worked with this aspect
	// When can be worked with this aspect
	@Around("execution(* com.my.web.controller.UserController.*(..))")
	public Object handleControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("TimeAspect->handleControllerMethods(): start");
		Object[] args = proceedingJoinPoint.getArgs();

		Arrays.stream(args).forEach(arg -> System.out.println("TimeAspect->handleControllerMethods() args: " + arg));

		long start = new Date().getTime();
		Object object = proceedingJoinPoint.proceed();
		System.out.println("TimeAspect: completed in " + (new Date().getTime() - start) + "milliseconds");
		System.out.println("TimeAspect->handleControllerMethods(): end");

		return object;
	}

}
