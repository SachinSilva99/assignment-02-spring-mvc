package com.sachin.projectmagement.config;

import com.sachin.projectmagement.WebAppInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = WebAppInitializer.class)
@EnableWebMvc
public class WebAppConfig {
}
