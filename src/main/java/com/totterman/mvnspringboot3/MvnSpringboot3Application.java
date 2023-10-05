package com.totterman.mvnspringboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class MvnSpringboot3Application {

	public static void main(String[] args) {
		SpringApplication.run(MvnSpringboot3Application.class, args);
	}

}
