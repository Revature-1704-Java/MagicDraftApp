package com.revature.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.revature.service.DBService;
import com.revature.service.PackGenerator;

@Configuration
public class PackGeneratorConfig {
	
	@Value("${cards}")
	private String cardsFilePath;

	@Bean
	public PackGenerator packGenerator(DBService dbService) throws IOException {
		return new PackGenerator(cardsFilePath, dbService);
	}
	
	@Bean
	ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
	    return new ErrorViewResolver() {
	        @Override
	        public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
	            return status == HttpStatus.NOT_FOUND
	                    ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
	                    : null;
	        }
	    };
	}
}
