package com.pedidos.restcontroller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidos.modelo.dto.ComercialDto;
import com.pedidos.modelo.entities.Comercial;
import com.pedidos.modelo.entities.Pedido;
import com.pedidos.service.ComercialService;
import com.pedidos.service.PedidoService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/comercial")
public class ComercialRestController {

	@Autowired 
	private ComercialService comercialService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/uno/{idComercial}")
	public Comercial uno(@PathVariable int idComercial){
		return comercialService.buscarUno(idComercial);
		
	}
	@PostMapping("/alta")
	public Comercial alta(@RequestBody ComercialDto comercialDto) {
		Comercial comercial = new Comercial();	
		modelMapper.map(comercialDto, comercial);
	
		return comercialService.altaComercial(comercial);
	}
	@DeleteMapping("/eliminar/{idComercial}")
	public String eliminar(@PathVariable int idComercial){
		if (comercialService.eliminarUno(idComercial))
			return "Comercial Eliminado correctamente";
		else
			return "Coemrcial NO se ha podido eliminar";
		
	}
	@GetMapping("/byCliente/{idCliente}")
    public List<Comercial> obtenerComercialesPorCliente(@PathVariable int idCliente) {
        return comercialService.buscarPorCliente(idCliente);
    }

	
	@GetMapping("/conpedidos")
	public List<Comercial> obtenerComercialesConPedido() {
	    return comercialService.bucarComercialesConPedido();
	}

    @GetMapping("/pedidos/{idComercial}") // Corregido para que coincida con el nombre del parámetro en el método
    public List<Pedido> obtenerPedidosDelComercial(@PathVariable int idComercial) {
        return pedidoService.findPedidosByComercialId(idComercial);
    }
	
	
}
