package com.example.jdbc.controllers;

import com.example.jdbc.models.Product;
import com.example.jdbc.responses.GetProductsResponse;
import com.example.jdbc.dto.ProductDTO;
import com.example.jdbc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<GetProductsResponse> getAll(){
		GetProductsResponse body = GetProductsResponse.builder().products(this.productService.getAll()).build();
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody ProductDTO productDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.productService.save(productDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(this.productService.getById(id));
	}


}
