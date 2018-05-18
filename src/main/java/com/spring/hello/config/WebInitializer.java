package com.spring.hello.config;

import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(MyMvcConfig.class);
		context.setServletContext(servletContext);
		
		javax.servlet.FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter",
				CharacterEncodingFilter.class);
		filter.setInitParameter("encoding",  String.valueOf(StandardCharsets.UTF_8));
		filter.addMappingForUrlPatterns(null, false, "/");
//		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/");
		
		
		Dynamic servlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
