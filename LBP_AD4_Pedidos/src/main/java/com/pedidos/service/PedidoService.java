package com.pedidos.service;

import java.util.List;

import com.pedidos.modelo.entities.Pedido;

public interface PedidoService {

	List<Pedido> findPedidosByComercialId(int idComercial);
}
