package com.spring.hello.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring.hello.web.interceptor.DemoInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.spring.hello")
@PropertySource(value = { "classpath:config/db.properties" })
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}
//	
//	@Bean
//	public StringHttpMessageConverter stringHttpMessageConverter() {
//		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
//	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	/**
	 * 处理静态数据
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	// @Override 处理静态数据
	// public void configureDefaultServletHandling(DefaultServletHandlerConfigurer
	// configurer) {
	// configurer.enable();
	// }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
		super.addInterceptors(registry);
	}
	
	/**
	 * 解决ResponseBody 乱码
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		converters.add(converter);
	}
}
