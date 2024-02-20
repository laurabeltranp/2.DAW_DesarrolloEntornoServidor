package com.cajero.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cajero.modelo.dao.CuentaDao;
import com.cajero.modelo.dao.MovimientoDao;
import com.cajero.modelo.entity.Cuenta;
import com.cajero.modelo.entity.Movimiento;

import jakarta.servlet.http.HttpSession;

@Controller

public class OperacionController {

	@Autowired
	CuentaDao cdao;
	
	@Autowired
	MovimientoDao mdao;
	
	@GetMapping("/ingresar")
	public String ingresar () {
		return "ingresar";
	}
	
	@GetMapping("/extraer")
	public String extraer () {
		return "extraer";
	}
	
	@PostMapping("/extraer")
	public String procesarExtraer (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			Cuenta cuenta = cdao.buscarCuenta(idCuenta);
			cuenta = cdao.retirarSaldo(cuenta, cantidad);
			if ( cuenta!= null) {
				Movimiento movimiento =  new Movimiento(0, null, 0, null, cuenta);
				mdao.altaMovimiento(movimiento);
				ratt.addFlashAttribute("mensaje", " Ha extraido " + cantidad + " en su cuenta " + cuenta.getIdCuenta());

			} else {
				ratt.addFlashAttribute("mensaje", " No hay suficiente saldo en su cuenta.");
			}
			return "redirect:/menu";		
	}
	
	/**
	 * Procesamos el ingresar dinero en nuestra cuenta
	 * @param ratt redirigir
	 * @param sesion cuenta iniciada
	 * @param cantidad a ingresar
	 * @return
	 */
	@PostMapping("/ingresar")
	public String procesarIngreso (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			Cuenta cuenta = cdao.buscarCuenta(idCuenta);
			cuenta = cdao.aumentoSaldo(cuenta, cantidad);
			Movimiento movimiento =  new Movimiento(0, null, 0, null, cuenta);
			mdao.altaMovimiento(movimiento);
			ratt.addFlashAttribute("mensaje", " Ha ingresado " + cantidad + " en su cuenta " + cuenta.getIdCuenta());
		return "redirect:/menu";
		
	}
	
	/**
	 * ver los moviminetos de la cuenta en la que hemos iniciado sesion
	 * @param model pasamos los datos a la vista
	 * @param sesion 
	 * @return
	 */
	
	@GetMapping("/movimientos")
	public String listarMovimientos (Model model, HttpSession sesion) {
		Integer idCuenta = (Integer) sesion.getAttribute("cuenta");

		model.addAttribute("movimientos", mdao.todosMovimientos(idCuenta));
		model.addAttribute("cuenta", cdao.buscarCuenta(idCuenta));
		System.out.println("cuenta" + cdao.buscarCuenta(idCuenta));

		return "movimientos";
	}
	
	@GetMapping("/transferencia")
	public String transferencia () {
		return "transferencia";
	}
	/**
	 * Procesamos el formulario de transferencia
	 * @param ratt att para redirigir
	 * @param sesion numero de cuenta
	 * @param cantidad a transferir
	 * @param idCuentaDestino cuenta que recibe el ingreso
	 * @return el menu
	 */
	
	@PostMapping("/transferencia")
	public String procesarTransferencia (RedirectAttributes ratt, HttpSession sesion, @RequestParam Double cantidad, @RequestParam Integer idCuentaDestino) {
			Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
			System.out.println("");
			Cuenta cuentaOrigen = cdao.buscarCuenta(idCuenta);
			Cuenta cuentaDestino = cdao.buscarCuenta(idCuentaDestino);
			
			
			if(cuentaDestino != null) {
				cuentaOrigen = cdao.retirarSaldo(cuentaOrigen, cantidad);
				if ( cuentaOrigen!= null) {
					int numOperacion = new Random().nextInt(100);
					Movimiento movimiento =  new Movimiento(numOperacion, new Date(), cantidad, "ingreso", cuentaDestino);
					mdao.altaMovimiento(movimiento);
					cuentaDestino =cdao.aumentoSaldo(cuentaDestino, cantidad);
					Movimiento movimientoDestino =  new Movimiento(numOperacion, new Date(), cantidad, "extracción", cuentaOrigen);
					mdao.altaMovimiento(movimientoDestino);
					mdao.altaMovimiento(movimiento);
					ratt.addFlashAttribute("mensaje", "Transferencia realizada.");
				} else {
					ratt.addFlashAttribute("mensaje", " No hay suficiente saldo en su cuenta.");
				}
			}
			else {
				ratt.addFlashAttribute("mensaje", "Cuenta destino no existe.");
			}

			return "redirect:/menu";		
	}
	
}

