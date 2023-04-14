package com.example.jdbc.repositories.impl;

import com.example.jdbc.repositories.ClientRepository;
import com.example.jdbc.models.Client;
import com.example.jdbc.dto.ClientDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientRepositoryImpl implements ClientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Client> getAll() {
		String sql = "SELECT * FROM Client";
		List<Client> clients = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
			return Client.builder()
					.id(resultSet.getLong("id"))
					.name(resultSet.getString("name"))
					.phone(resultSet.getString("phone"))
					.build();
		});
		return clients;
	}

	@Override
	public Client save(ClientDTO clientDTO) {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName("create_client")
					.declareParameters(
							new SqlParameter("client_name", Types.VARCHAR),
							new SqlParameter("client_phone", Types.VARCHAR),
							new SqlOutParameter("out_client_id", Types.INTEGER),
							new SqlOutParameter("out_client_name", Types.VARCHAR),
							new SqlOutParameter("out_client_phone", Types.VARCHAR)
					);

			MapSqlParameterSource inParams = new MapSqlParameterSource();
			inParams.addValue("client_name", clientDTO.getName());
			inParams.addValue("client_phone", clientDTO.getPhone());

			Map<String, Object> outParams = jdbcCall.execute(inParams);

			Client newClient = Client.builder()
					.id(((Number) outParams.get("out_client_id")).longValue())
					.name((String) outParams.get("out_client_name"))
					.phone((String) outParams.get("out_client_phone"))
					.build();

			return newClient;
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			throw new RuntimeException("Error al crear client");
		}
	}

	@Override
	public Client getById(Long id) {
		String sql = "SELECT * FROM Client WHERE id=?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) -> {
				Client client = Client.builder()
						.id(resultSet.getLong("id"))
						.name(resultSet.getString("name"))
						.phone(resultSet.getString("phone"))
						.build();
				return client;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("El client  " + id + " no existe");
		}
	}
}