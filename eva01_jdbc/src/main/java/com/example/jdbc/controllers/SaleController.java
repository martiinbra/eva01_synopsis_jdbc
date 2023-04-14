package com.example.jdbc.controllers;

import com.example.jdbc.models.Sale;
import com.example.jdbc.dto.SaleDTO;
import com.example.jdbc.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sales")
public class SaleController {
	@Autowired
	private SaleService saleService;

	@PostMapping
	public ResponseEntity<Sale> createSale(@RequestBody SaleDTO saleDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.saleService.createSale(saleDTO));
	}
}
