package com.pedidos.modelo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComercialDto {
	
	private int idComercial;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private double comision;

	

}
