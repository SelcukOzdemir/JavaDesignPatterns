package com.sso.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	 
	 @Before("execution(* com.sso.aopdemo.service.*.*(..))")
	 public void beforeMethod(JoinPoint joinPoint) {
		 logger.info("Before --> Method çağırılıyor: " + joinPoint.getSignature().toShortString());
	 }
	 
	 @AfterReturning(pointcut = "execution(* com.sso.aopdemo.service.*.*(..))", returning = "result")
	 public void afterReturning(JoinPoint joinPoint, Object result) {
		 logger.info("AfterReturning --> Metod tamamlandı: " + joinPoint.getSignature().toShortString());
		 logger.info("Dönen Sonuç: " + result);
	 }

	 @AfterThrowing(pointcut = "execution(* com.sso.aopdemo.service.*.*(..))", throwing = "exception")
	 public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
	     logger.error("AfterThrowing --> Hata oluştu: " + joinPoint.getSignature().toShortString(), exception);
	 }
	 
	 @After("execution(* com.sso.aopdemo.service.*.*(..))")
	 public void afterMethod(JoinPoint joinPoint) {
	     logger.info("After --> Metod tamamlandı: " + joinPoint.getSignature().toShortString());
	 }
	 
	 @Around("execution(* com.sso.aopdemo.service.*.*(..))")
	 public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
	     long start = System.currentTimeMillis();
	     System.out.println("Around -->");
	     Object result = joinPoint.proceed(); // Metodu çalıştır
	     System.out.println("Around<--");
	     long end = System.currentTimeMillis();
	     logger.info(joinPoint.getSignature().toShortString() + " çalışma süresi: " + (end - start) + " ms");
	     
	     return result;
	 }
}
