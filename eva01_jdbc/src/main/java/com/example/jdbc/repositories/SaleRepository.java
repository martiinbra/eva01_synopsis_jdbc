package com.example.jdbc.repositories;

import com.example.jdbc.models.Sale;
import com.example.jdbc.dto.SaleDTO;

import java.util.List;

public interface SaleRepository {
	List<Sale> getSales();

	Sale createSale(SaleDTO saleDTO);
}
