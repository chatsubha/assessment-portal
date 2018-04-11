package com.portal.controller;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.qos.logback.classic.LoggerContext;

@SpringBootApplication(scanBasePackages={
"com.portal"})
public class AssessmentPortalApplication {
	static Logger logger= org.slf4j.LoggerFactory.getLogger(AssessmentPortalApplication.class);
	public static void main(String[] args) {
		System.setProperty("root_path","src/main/resources");
		SpringApplication.run(AssessmentPortalApplication.class, args);
		logger.error("No error");
		
	}
}
