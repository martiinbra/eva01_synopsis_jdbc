package com.example.jdbc.services.impl;

import com.example.jdbc.repositories.ProductRepository;
import com.example.jdbc.models.Product;
import com.example.jdbc.dto.ProductDTO;
import com.example.jdbc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ProductDTO implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return this.productRepository.getAll();
	}

	@Override
	public Product save(ProductDTO productDTO) {
		return this.productRepository.save(productDTO);
	}

	@Override
	public Product getById(Long id) {
		return this.productRepository.getById(id);
	}
}
