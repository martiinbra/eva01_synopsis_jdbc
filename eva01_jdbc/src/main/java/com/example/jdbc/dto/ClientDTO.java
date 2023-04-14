package com.example.jdbc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
	private String name;
	private String phone;
}
