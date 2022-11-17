package com.bicycle.map;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bicycle.map.interceptor.MyInterceptor;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer implements WebMvcConfigurer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BicyclemapApplication.class);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new MyInterceptor())
//		.addPathPatterns("/test/a","/board/write");
	}

}
