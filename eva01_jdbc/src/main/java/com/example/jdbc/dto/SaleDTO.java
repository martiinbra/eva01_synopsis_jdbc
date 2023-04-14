package com.example.jdbc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
	private int idClient;
	private List<SaleDetailDTO> saleDetailsDTO;
}
