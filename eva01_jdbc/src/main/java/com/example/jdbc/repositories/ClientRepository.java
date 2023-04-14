package com.example.jdbc.repositories;

import com.example.jdbc.models.Client;
import com.example.jdbc.dto.ClientDTO;

import java.util.List;

public interface ClientRepository {
	List<Client> getAll();

	Client save(ClientDTO clientDTO);

	Client getById(Long id);
}
