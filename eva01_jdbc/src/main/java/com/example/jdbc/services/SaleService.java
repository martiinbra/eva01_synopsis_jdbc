package com.example.jdbc.services;

import com.example.jdbc.models.Sale;
import com.example.jdbc.dto.SaleDTO;

import java.util.List;

public interface SaleService {
	List<Sale> getSales();

	Sale createSale(SaleDTO saleDTO);
}
