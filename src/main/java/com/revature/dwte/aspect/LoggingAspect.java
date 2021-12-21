package com.revature.dwte.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//	@Before("execution(* com.revature.dwte.dao.*.*(...))") // any class in the dao package
//	public void logDaoMethodsBefore(JoinPoint jp) {
//
//		MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//
//		String methodName = methodSignature.getName();
//		logger.info("DAO method " + methodName + " is about to be executed");
//	}
//	
//	@Before("execution(* com.revature.dwte.service.*.*(...))") // any class in the dao package
//	public void logServiceMethodsBefore(JoinPoint jp) {
//
//		MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//
//		String methodName = methodSignature.getName();
//		logger.info("SERVICE method " + methodName + " is about to be executed");
//	}
//	
//	@Before("execution(* com.revature.dwte.controller.*.*(...))") // any class in the dao package
//	public void logControllerMethodsBefore(JoinPoint jp) {
//
//		MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//
//		String methodName = methodSignature.getName();
//		logger.info("CONTROLLER method " + methodName + " is about to be executed");
//	}

}
