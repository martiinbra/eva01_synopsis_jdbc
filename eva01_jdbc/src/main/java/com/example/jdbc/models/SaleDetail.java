package com.example.jdbc.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {
	private Long id;

	private Long idProduct;

	private int quantity;

	private Double subtotal;
}
