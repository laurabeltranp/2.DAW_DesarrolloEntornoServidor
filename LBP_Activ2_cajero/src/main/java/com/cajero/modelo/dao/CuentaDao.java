package com.cajero.modelo.dao;

import java.util.List;

import com.cajero.modelo.entity.Cuenta;

public interface CuentaDao {

	Cuenta buscarCuenta (int idCuenta);
	List <Cuenta> todasCuentas();
	Cuenta aumentoSaldo(Cuenta cuenta, Double cantidad);
	Cuenta retirarSaldo(Cuenta cuenta, Double cantidad);
	boolean transferirSaldo(Cuenta cuentaDestino, Cuenta cuentaOrigen, Double cantidad);
}
