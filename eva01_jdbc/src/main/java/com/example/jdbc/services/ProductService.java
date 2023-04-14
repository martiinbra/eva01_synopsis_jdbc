package com.example.jdbc.services;

import com.example.jdbc.models.Product;
import com.example.jdbc.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	List<Product> getAll();

	Product save(ProductDTO productDTO);

	Product getById(Long id);
}
