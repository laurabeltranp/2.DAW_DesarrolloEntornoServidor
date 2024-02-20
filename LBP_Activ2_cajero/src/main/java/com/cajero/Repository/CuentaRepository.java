package com.cajero.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cajero.modelo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

}
