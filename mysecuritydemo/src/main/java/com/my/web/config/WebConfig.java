package com.my.web.config;

import com.my.web.filter.TimeFilter;
import com.my.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private TimeInterceptor timeInterceptor;

	/*@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

	}*/

	//@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();

		filterRegistrationBean.setFilter(timeFilter);

		// Which urls will be filtered
		List<String> filteredUrls = new ArrayList<>();

		// All urls will be filtered
		filteredUrls.add("/*");
		filterRegistrationBean.setUrlPatterns(filteredUrls);

		return filterRegistrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(timeInterceptor);
	}
}
