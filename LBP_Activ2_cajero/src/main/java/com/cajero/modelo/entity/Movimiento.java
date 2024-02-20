package com.cajero.modelo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="movimientos")
public class Movimiento {
	@Id
	@Column(name="id_movimiento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMovimiento;
	@Column(name="fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Column(name="cantidad")
	private double cantidad;
	@Column(name="operacion")
	private String operacion;
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;
	

	public String getOperacion(int pos) {
		
		return ""+ cantidad;
		
	}
}
