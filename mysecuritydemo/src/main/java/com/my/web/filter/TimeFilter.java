package com.my.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 *	If the class has @Component
 *		All urls will be filtered by this class.
 *	else
 *		you can define which urls can be filtered by this class in com.my.web.config.WebConfig
 */
//@Component
public class TimeFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----------TimeFilter Init----------");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("TimeFilter: start");
		long start = new Date().getTime();
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println("TimeFilter: completed in " + (new Date().getTime() - start) + "milliseconds");
		System.out.println("TimeFilter: finish");
	}

	@Override
	public void destroy() {
		System.out.println("----------TimeFilter Destroy----------");
	}
}
