package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DemoApplication.class, args);

		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		/*
		for (String name: applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		*/
	}
}
