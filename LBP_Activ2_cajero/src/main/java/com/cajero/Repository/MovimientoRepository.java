package com.cajero.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cajero.modelo.entity.Movimiento;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cajero.modelo.entity.Movimiento;



public interface MovimientoRepository  extends JpaRepository<Movimiento, Integer>{
	/**
	 * Query interna para obtener los movimientos de una cuenta
	 * @param idCuenta pasamos el id cuenta del que queremos saber los movimientos
	 * @return
	 */
	@Query("select m from Movimiento m where m.cuenta.idCuenta = ?1")
	public List <Movimiento> findMovimientoPorCuenta(int idCuenta);
	
}
