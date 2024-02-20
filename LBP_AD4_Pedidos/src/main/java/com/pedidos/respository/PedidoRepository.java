package com.pedidos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedidos.modelo.entities.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query("SELECT p FROM Pedido p WHERE p.comercial.id = :idComercial")
	public List<Pedido> buscarPedidosPorComercial(int idComercial);
}
