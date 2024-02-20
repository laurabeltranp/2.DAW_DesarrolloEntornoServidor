package com.cajero.modelo.dao;

import java.util.List;

import com.cajero.modelo.entity.Movimiento;

public interface MovimientoDao {
	
	//List<Movimiento> movimientosDeUnaCuenta(int idCuenta);
	Movimiento buscarMovimiento (int idMovimiento);
	Movimiento altaMovimiento (Movimiento movimiento);
	List<Movimiento> todosMovimientos(int idCuenta);
	
}
