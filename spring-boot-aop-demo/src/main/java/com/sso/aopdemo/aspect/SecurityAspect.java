package com.sso.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sso.aopdemo.service.SecurityService;

@Aspect
@Component
public class SecurityAspect {

	@Autowired
	private SecurityService securityService;
	
	@Before("@annotation(com.sso.aopdemo.service.AdminOnly)")
	public void checkAdminAccess(JoinPoint joinpoint) {
		String role = securityService.getCurrentUserRole();
		if(!role.equals("ADMIN")) {
			throw new SecurityException("Yetkisiz erişim! Bu metod sadece ADMIN rolü için geçerlidir.");
		}
	}
}
