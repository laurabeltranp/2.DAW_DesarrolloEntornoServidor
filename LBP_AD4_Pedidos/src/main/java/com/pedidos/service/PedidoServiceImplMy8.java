package com.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.modelo.entities.Pedido;
import com.pedidos.respository.PedidoRepository;

@Service
public class PedidoServiceImplMy8 implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<Pedido> findPedidosByComercialId(int idComercial) {
		// TODO Auto-generated method stub
		return pedidoRepository.buscarPedidosPorComercial(idComercial);
	}
	

}
