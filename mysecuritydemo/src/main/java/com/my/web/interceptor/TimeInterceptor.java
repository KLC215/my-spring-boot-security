package com.my.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
		System.out.println("TimeInterceptor->preHandle(): start");

		System.out.println("TimeInterceptor class name: " + ((HandlerMethod) handler).getBean().getClass().getName());
		System.out.println("TimeInterceptor method name: " + ((HandlerMethod) handler).getMethod().getName());

		httpServletRequest.setAttribute("startTime", new Date().getTime());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("TimeInterceptor->postHandle(): start");
		long start = (long) httpServletRequest.getAttribute("startTime");
		System.out.println("TimeInterceptor: completed in " + (new Date().getTime() - start) + "milliseconds");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
		System.out.println("TimeInterceptor->afterCompletion(): start");
		System.out.println("TimeInterceptor exception: " + e);
		long start = (long) httpServletRequest.getAttribute("startTime");
		System.out.println("TimeInterceptor: completed in " + (new Date().getTime() - start) + "milliseconds");
	}
}
