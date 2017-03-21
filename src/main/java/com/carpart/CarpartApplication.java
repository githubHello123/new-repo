package com.carpart;

import com.carpart.servlet.RandCodeImageServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarpartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpartApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean randCodeImageServlet(){
		ServletRegistrationBean bean = new ServletRegistrationBean(new RandCodeImageServlet());
		bean.addUrlMappings("/randCodeImage");
		return bean;
	}
}
