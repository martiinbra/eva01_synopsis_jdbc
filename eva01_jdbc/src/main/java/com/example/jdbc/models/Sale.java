package com.example.jdbc.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
	private Long id;
	private Long idClient;

	private double total;

	private List<SaleDetail> saleDetails;
}
