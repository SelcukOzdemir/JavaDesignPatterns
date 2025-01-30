package com.sso.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	 public String getUserById(Long id) {
	        if (id == 0) {
	            throw new RuntimeException("Geçersiz kullanıcı ID");
	        }
	        return "Kullanıcı: " + id;
	    }
}
