package com.cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cajero.Repository.MovimientoRepository;
import com.cajero.modelo.entity.Movimiento;

@Repository
public class MovimientoDaoimplMy8Jpa implements MovimientoDao{

	@Autowired
	MovimientoRepository movrepo;
	/*
	@Override
	public List<Movimiento> movimientosDeUnaCuenta(int idCuenta) {
		return movrepo.findMovimientoPorCuenta(idCuenta);
	}
	 */
	
	/**
	 * Buscamos movimiento por id de movimiento
	 */
	@Override
	public Movimiento buscarMovimiento(int idMovimiento) {
		// TODO Auto-generated method stub
		return movrepo.findById(idMovimiento).orElse(null);
	}
	/**
	 * Damos de alta un movimiento
	 */
	@Override
	public Movimiento altaMovimiento(Movimiento movimiento) {
		try {
			return movrepo.save(movimiento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	/**
	 * Vemos todos los movmientos para ello hacemos uso de la query del ropository
	 */
	@Override
	public List<Movimiento> todosMovimientos(int idCuenta) {
		return movrepo.findMovimientoPorCuenta(idCuenta);

	}

	
}

