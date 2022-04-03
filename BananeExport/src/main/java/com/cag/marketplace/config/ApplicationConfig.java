package com.cag.marketplace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Value("${allowed.origins}")
	private String[] origines;

	@Value("${spring.data.rest.base-path}")
	private String[] basePath;
	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(basePath+ "/**")
			.allowedOrigins(origines)
			.allowCredentials(false);
	}
	
	
	
	
}
