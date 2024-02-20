package com.pedidos.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedidos.modelo.entities.Comercial;




public interface ComercialRepository extends JpaRepository<Comercial, Integer>{
	
	@Query("SELECT DISTINCT c FROM Comercial c " +
		       "JOIN Pedido p ON c.id = p.comercial.id " +
		       "JOIN Cliente cl ON p.cliente.id = cl.id " +
		       "WHERE cl.id = :idCliente")
		public List<Comercial> buscarPorCliente(int idCliente);
	
	@Query("SELECT c FROM Comercial c JOIN Pedido p ON c.idComercial = p.comercial.idComercial")
	List<Comercial> buscarComercialesConPedidos();
	
}
