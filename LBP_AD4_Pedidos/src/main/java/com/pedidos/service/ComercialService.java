package com.pedidos.service;

import java.util.List;

import com.pedidos.modelo.entities.Comercial;

public interface ComercialService {

	Comercial altaComercial (Comercial comercial);
	boolean eliminarUno (int idComercial);
	Comercial buscarUno (int idComercial);
	List <Comercial> buscarPorCliente (int idCliente);
	List<Comercial> bucarComercialesConPedido();
}
