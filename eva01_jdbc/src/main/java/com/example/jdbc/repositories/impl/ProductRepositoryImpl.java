package com.example.jdbc.repositories.impl;

import com.example.jdbc.repositories.ProductRepository;
import com.example.jdbc.models.Product;
import com.example.jdbc.dto.ProductDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;


import java.sql.Types;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAll() {
		String sql = "SELECT * FROM Product";
		List<Product> products = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
			return Product.builder()
					.id(resultSet.getLong("id"))
					.description(resultSet.getString("description"))
					.cost(resultSet.getDouble("cost"))
					.build();
		});
		return products;
	}

	@Override
	public Product save(ProductDTO productDTO) {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName("create_product")
					.declareParameters(
							new SqlParameter("product_description", Types.VARCHAR),
							new SqlParameter("product_cost", Types.VARCHAR),
							new SqlOutParameter("out_product_id", Types.INTEGER),
							new SqlOutParameter("out_product_description", Types.VARCHAR),
							new SqlOutParameter("out_product_cost", Types.VARCHAR)
					);

			MapSqlParameterSource inParams = new MapSqlParameterSource();
			inParams.addValue("product_description", productDTO.getDescription());
			inParams.addValue("product_cost", productDTO.getCost());

			Map<String, Object> outParams = jdbcCall.execute(inParams);

			Product newProduct = Product.builder()
					.id(((Number) outParams.get("out_product_id")).longValue())
					.description((String) outParams.get("out_product_description"))
					.cost(Double.parseDouble((String) outParams.get("out_product_cost")))
					.build();

			return newProduct;
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			throw new RuntimeException("Error al crear product");
		}
	}

	@Override
	public Product getById(Long id) {
		String sql = "SELECT * FROM Product WHERE id=?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) -> {
				Product product = Product.builder()
						.id(resultSet.getLong("id"))
						.description(resultSet.getString("description"))
						.cost(resultSet.getDouble("cost"))
						.build();
				return product;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("No se encontró el Product con id " + id, e);
		}
	}
}