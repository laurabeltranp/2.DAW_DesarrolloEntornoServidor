package com.pedidos.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos.modelo.entities.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
