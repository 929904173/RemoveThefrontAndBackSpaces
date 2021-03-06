package com.dscm.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig  {
	@Bean
	public FilterRegistrationBean parmsFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.setFilter(new ParamsFilter());
		registration.addUrlPatterns("/*");
		registration.setName("paramsFilter");
		registration.setOrder(Integer.MAX_VALUE - 1);
		return registration;
	}

}
