package com.example.jdbc.responses;

import com.example.jdbc.models.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductsResponse {
	List<Product> products;
}
