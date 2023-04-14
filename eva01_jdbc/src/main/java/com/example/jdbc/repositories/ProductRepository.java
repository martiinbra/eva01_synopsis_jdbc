package com.example.jdbc.repositories;

import com.example.jdbc.models.Product;
import com.example.jdbc.dto.ProductDTO;

import java.util.List;

public interface ProductRepository {
	List<Product> getAll();

	Product save(ProductDTO productDTO);

	Product getById(Long id);
}
