package com.example.jdbc.services.impl;

import com.example.jdbc.repositories.ClientRepository;
import com.example.jdbc.models.Client;
import com.example.jdbc.dto.ClientDTO;
import com.example.jdbc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> getAll() {
		return this.clientRepository.getAll();
	}

	@Override
	public Client save(ClientDTO clientDTO) {
		return this.clientRepository.save(clientDTO);
	}

	@Override
	public Client getById(Long id) {
		return this.clientRepository.getById(id);
	}
}
