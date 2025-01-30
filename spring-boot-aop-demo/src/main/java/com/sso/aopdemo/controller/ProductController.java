package com.sso.aopdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sso.aopdemo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	public  ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{id}")
	public void getProduct(@PathVariable int id) {
		System.out.println("product");
		productService.deleteProduct(id);
	}
}
