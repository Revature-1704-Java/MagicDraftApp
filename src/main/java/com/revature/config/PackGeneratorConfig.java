package com.revature.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.service.PackGenerator;

@Configuration
public class PackGeneratorConfig {
	
	@Value("${cards}")
	private String cardsFilePath;

	@Bean
	public PackGenerator packGenerator() throws IOException {
		return new PackGenerator(cardsFilePath);
	}
}
