package com.example.jdbc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailDTO {
	private int idProduct;
	private int quantity;
}
