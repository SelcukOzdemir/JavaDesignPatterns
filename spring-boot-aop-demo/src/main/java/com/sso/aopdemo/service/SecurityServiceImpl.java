package com.sso.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public String getCurrentUserRole() {
		 return "USER";
	}

}
