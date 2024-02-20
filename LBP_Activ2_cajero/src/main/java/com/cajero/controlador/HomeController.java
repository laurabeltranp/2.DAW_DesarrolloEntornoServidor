package com.cajero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cajero.modelo.dao.CuentaDao;
import com.cajero.modelo.entity.Cuenta;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private CuentaDao cdao;
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	/**
	 * Manda el form de inicio de sesion
	 */
	@GetMapping("/login")
	public String mostrarFormularioLogin() {
		
			return "formLogin";
		}
	/**
	 * Procesa el form de inicio de sesion
	 */
	
	@PostMapping("/login")
	public String procLogin(RedirectAttributes ratt, HttpSession sesion, @RequestParam int idCuenta) {
		Cuenta cuenta = cdao.buscarCuenta(idCuenta);
		System.out.println("en el ogin cuenta encontrada cuenta " + cuenta);
		if (cuenta != null) {
			sesion.setAttribute("cuenta", idCuenta);
			System.out.println("establece sesion " + sesion.getAttribute("cuenta"));
			return "redirect:/menu";
		}
		System.out.println("no FUNCIONA");
		ratt.addFlashAttribute("mensaje", "Cuenta no existe.");
		return "redirect:/";
	}

	/**
	 * Para ver el menu
	 * @param sesion si se inicia sesion veremos nuestros datos
	 * @param model
	 * @return
	 */
	@GetMapping("/menu")
	public String menu(HttpSession sesion, Model model) {
		System.out.println("Funciona menu");
		if (sesion.getAttribute("cuenta") == null) {
			System.out.println("id cuenta es nulo redirige a /");
			return "redirect:/";
		}
		System.out.println("leyendo sesion");
		Integer idCuenta = (Integer) sesion.getAttribute("cuenta");
		System.out.println("buscando cuenta y poniendo saldo");
		Cuenta cuenta = cdao.buscarCuenta(idCuenta);
		model.addAttribute("saldo", cuenta.getSaldo());
		return "menu";
	}


}
