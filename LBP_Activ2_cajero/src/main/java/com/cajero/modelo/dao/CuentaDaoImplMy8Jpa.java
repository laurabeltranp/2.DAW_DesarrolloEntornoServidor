package com.cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cajero.Repository.CuentaRepository;
import com.cajero.modelo.entity.Cuenta;

@Repository
public class CuentaDaoImplMy8Jpa implements CuentaDao{

	@Autowired
	private CuentaRepository crepo;
	
	@Override
	public Cuenta buscarCuenta(int idCuenta) {
		
		return crepo.findById(idCuenta).orElse(null);
	}
	@Override
	public List<Cuenta> todasCuentas() {
		return crepo.findAll();
	}
	
	@Override
	public Cuenta aumentoSaldo(Cuenta cuenta, Double cantidad) {
		cuenta.setSaldo(cuenta.getSaldo() + cantidad);
		return crepo.save(cuenta);
	}

	
	@Override
	public Cuenta retirarSaldo(Cuenta cuenta, Double cantidad) {
		if (cuenta.getSaldo() >= cantidad) {
			cuenta.setSaldo(cuenta.getSaldo() - cantidad);
			return crepo.save(cuenta);
		}
		return null;
	}

	
	@Override
	public boolean transferirSaldo(Cuenta cuentaDestino, Cuenta cuentaOrigen, Double cantidad) {
		if (this.aumentoSaldo(cuentaOrigen, cantidad) != null) {
			this.retirarSaldo(cuentaDestino, cantidad);
			return true;
		}
		return false;
	}
}
