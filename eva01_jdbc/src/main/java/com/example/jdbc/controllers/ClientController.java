package com.example.jdbc.controllers;

import com.example.jdbc.models.Client;
import com.example.jdbc.responses.GetClientsResponse;
import com.example.jdbc.dto.ClientDTO;
import com.example.jdbc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	@GetMapping
	public ResponseEntity<GetClientsResponse> getAll(){
		GetClientsResponse body = GetClientsResponse.builder().clients(this.clientService.getAll()).build();
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@PostMapping
	public ResponseEntity<Client> save(@RequestBody ClientDTO clientDTO){
		return ResponseEntity.status(HttpStatus.OK).body(this.clientService.save(clientDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getById(@PathVariable(name = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(this.clientService.getById(id));
	}


}
