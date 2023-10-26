package eventos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;

@Controller
public class HomeController {
	@Autowired
	private EventoDao edao;
	
	@Autowired
	private TipoDao tdao;
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		model.addAttribute("eventos",edao.findAll());
		return "home";
	}
	@GetMapping("/tipo")
	public String mostrarTipos(Model model) {
		model.addAttribute("tipo",tdao.findAll());
		return "tipo";
		
	}
	
}
