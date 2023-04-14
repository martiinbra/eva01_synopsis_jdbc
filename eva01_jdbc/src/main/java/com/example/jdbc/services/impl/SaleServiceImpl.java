package com.example.jdbc.services.impl;

import com.example.jdbc.repositories.SaleRepository;
import com.example.jdbc.models.Sale;
import com.example.jdbc.dto.SaleDTO;
import com.example.jdbc.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleRepository saleRepository;

	@Override
	public List<Sale> getSales() {
		return null;
	}

	@Override
	public Sale createSale(SaleDTO saleDTO) {
		return this.saleRepository.createSale(saleDTO);
	}
}
