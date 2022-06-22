package com.douzone.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:com/douzone/mysite.config/web/fileupload.properties")
public class FileUploadConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver mulipartResolver = new CommonsMultipartResolver();
		mulipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
		mulipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
		mulipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));
		
		return mulipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
				.addResourceLocations("file:"+env.getProperty("fileupload.uploadLocation"));
		
	}
}
