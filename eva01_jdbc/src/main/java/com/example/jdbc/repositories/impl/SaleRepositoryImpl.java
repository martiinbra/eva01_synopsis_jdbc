package com.example.jdbc.repositories.impl;

import com.example.jdbc.models.SaleDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.jdbc.repositories.SaleRepository;
import com.example.jdbc.models.Sale;
import com.example.jdbc.dto.SaleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class SaleRepositoryImpl implements SaleRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger log = LoggerFactory.getLogger(SaleRepositoryImpl.class);

	@Override
	public List<Sale> getSales() {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("get_sales");

		Map<String, Object> out = jdbcCall.execute();
		List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("#result-set-1");

		List<Sale> sales = new ArrayList<>();
		for (Map<String, Object> map : result) {
			Long id = ((Number) map.get("id")).longValue();
			Long idClient = ((Number) map.get("idClient")).longValue();
			double total = ((Number) map.get("total")).doubleValue();

			Sale sale = Sale.builder()
					.id(id)
					.idClient(idClient)
					.total(total)
					.build();

			sales.add(sale);
		}

		return sales;
	}

	@Override
	public Sale createSale(SaleDTO saleDTO) {
		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = null;
		try {
			inputJson = objectMapper.writeValueAsString(saleDTO);
		} catch (JsonProcessingException e) {
			log.error("Error SaleDTO", e.getMessage());
			throw new RuntimeException("Error SaleDTO", e);
		}
		try{
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName("create_sale")
					.declareParameters(
							new SqlParameter("input_data", Types.VARCHAR),
							new SqlOutParameter("result", Types.VARCHAR)
					);
			Map<String, Object> inParams = Map.of("input_data", inputJson);
			Map<String, Object> outParams = jdbcCall.execute(inParams);
			var result = (String) outParams.get("result");
			log.debug(result);
		}catch (DataAccessException e){
			log.error("Error al crear Sale", e.getMessage());
			throw new RuntimeException("Error al crear Sale", e);
		}
		return null;
	}
}