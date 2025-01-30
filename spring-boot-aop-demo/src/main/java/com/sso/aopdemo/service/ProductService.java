package com.sso.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@AdminOnly
    public void deleteProduct(int productId) {
        System.out.println("Ürün silindi: " + productId);
    }
}
