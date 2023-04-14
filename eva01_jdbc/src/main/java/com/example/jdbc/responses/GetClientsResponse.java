package com.example.jdbc.responses;

import com.example.jdbc.models.Client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetClientsResponse {
	private List<Client> clients;
}
