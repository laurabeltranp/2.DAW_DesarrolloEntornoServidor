package eventos.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.TipoDao;
import eventos.modelo.javabean.Evento;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	@Qualifier("evento")
	@Autowired
	private EventoDao edao;

	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		
		Evento even = edao.findById(idEvento);
		if (even != null) {
			model.addAttribute("evento", even);
			return "formEditar";
		}
		else {
			model.addAttribute("mensaje", "Este cliente no existe");
			return "forward:/";
		}
		
	}
	/**
	 * Con este método procedemos a editar los campos de un evento y que queden registrados correctamente edao lo utilizamos para usar la implementacion del interface
	 * @param evento clase que corresponde al javabean
	 * @param idEvento atributo de la clase evento 
	 * @param ratt
	 * @return retornamos la lista actualizada
	 */
	
	@PostMapping("/editar/{id}")
	public String procEditarEvento(Evento evento, @PathVariable("id") int idEvento, 
			RedirectAttributes ratt) {
		System.out.println("procEditarEvento()");
			System.out.println(evento.getIdEvento());
		//evento.setIdEvento(idEvento);
		if (edao.updateOne(evento) == 1)
			ratt.addFlashAttribute("mensaje", "Modificación realizada");
		else
			ratt.addFlashAttribute("mensaje", "Modificacion NOOO  realizada");
		
		
		return "redirect:/";
	}
/**
 * Metodo para realizar alta de evento y procesarlo
 * IMPORTANTE: puede que al dar de alta el primer alta de un error, volviendo a la pagina principal saldrá, el segundo alta debería funcionar sin problemas.
 * @param evento
 * @param ratt
 * @return si el evento se da de alta correctamente te redigirá a la pagina principal sino dará error
 */
	@PostMapping("/alta")
	public String proFormEvento(Evento evento, RedirectAttributes ratt) {
		System.out.println("dando alta nueva");
		// actualizar evento.setFecha(new Date());
		if (edao.insert(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Alta de evento realizada");
			System.out.println(evento);
		}else
			ratt.addFlashAttribute("mensaje", "Alta de evento no realizada");
		
		return "redirect:/";
	}
	
	@GetMapping("/alta")
	public String mostrarFormAlta() {
		
		return "formAlta";
	}
	
/**
 * Con este método tratamos de ver en detalle un evento en concreto, buscandolo por un id que tenga asignado
 * @param idEvento metodo de identificación de un evento que tenemos declarados en una lista que es cargarLista en EventosDaoImpl
 * @param model
 * @return devuelve la lista del detalle del evento que deseamos, si no existe no saldrá
 */
	@GetMapping("/detalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento, Model model) {
		
		Evento even = edao.findById(idEvento);
		if (even != null) {
			model.addAttribute("evento", even);
			return "detalle";
		}
		else {
			model.addAttribute("mensaje", "Este evento no existe");
			return "forward:/";
		}
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento, Model model) {
		
		if (edao.delete(idEvento) == 1)
			model.addAttribute("mensaje", "Evento eliminado correctamente");
		else
			model.addAttribute("mensaje", "Este evento NOO se ha podido eliminar");
		
		return "forward:/";
		
	}
	/**
	 * Método para cancelar o cambiar el estado de un vento a cancelado para ello necesitamos el metodo correspondiente del daoImpl y el id del que tenemos que actualizar
	 * @param id
	 * @return una vez cancelado lo podremos ver por consola o en la pagina principal si le damos a detalle
	 */
	 @GetMapping("/cancelar/{id}")
	    public String cancelarEvento(@PathVariable int id) {
	        edao.cancelarEvento(id);
	        // Comprueba el estado por consola
	        Evento evento = edao.findById(id);
	        System.out.println("Evento " + id + " ha sido cancelado. Estado: " + evento.getEstado());

	        return "redirect:/";
	    }

	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
