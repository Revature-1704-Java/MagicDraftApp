package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AppLogger {

	private static Logger logger = LogManager.getLogger("Logger");
	
	//@Before("execution(* com.revature.controller.UserValidation.postLogin(..))")
	@Before("execution(* com.revature.controller.UserValidation.postLogin(..))")
	public void logginIn(JoinPoint jp) {
		Object[] args = jp.getArgs();
		logger.info(args[0] + " attemping to login.");
	}
	
}
