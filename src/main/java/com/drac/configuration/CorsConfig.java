package com.drac.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
@Override
public void addCorsMappings(CorsRegistry corsRegistry){
	corsRegistry.addMapping("/**").allowedHeaders("*").allowedMethods("*");
}

}
