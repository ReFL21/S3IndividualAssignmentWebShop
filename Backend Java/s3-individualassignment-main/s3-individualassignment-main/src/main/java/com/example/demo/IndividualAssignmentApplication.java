package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndividualAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndividualAssignmentApplication.class, args);
	}


//	@Configuration
//	@EnableWebMvc
//	public class WebConfig implements WebMvcConfigurer {
//
//		@Override
//		public void addCorsMappings(CorsRegistry registry) {
//
//			registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("*");
//
//		}
//	}
}
