package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import eventos.modelo.javabean.Evento;

@Qualifier("evento")
@Repository
public class EventoDaoImpl implements EventoDao {

	private List<Evento> lista;
	
	private TipoDao tipoEvento;
	
	
	private static int idAuto;
	
	static {
		idAuto=0;
	}
	public  EventoDaoImpl() {
		lista = new ArrayList<>();
		tipoEvento = new TipoDaoImpl();
		cargarLista();
	}
	
	private void cargarLista() {
		lista.add(new Evento(01, "Graduacion", "Bachillerato Guadalupe", new Date(), 200, 1000, "ACTIVO", 150, tipoEvento.findById(1)));
		lista.add(new Evento(02,"Cumpleaños", "Cumpleaños Tomasín", new Date(), 120, 100, "ACTIVO", 200, tipoEvento.findById(2)));
		lista.add(new Evento(03,"Boda", "Boda Camilo Sexto", new Date(), 300, 10000, "ACTIVO", 200, tipoEvento.findById(3)));
		idAuto = 3;
		
	}
	
	@Override
	public int insert(Evento evento) {
		System.out.println("insertando nuevo evento");
		if (!lista.contains(evento)) {
			evento.setIdEvento(++idAuto);
			lista.add(evento);
			
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int idEvento) {
		Evento even = findById(idEvento);
		if (even == null) 
			return 0;
		
		return lista.remove(even) ? 1 : 0;
	}

	@Override
	public int updateOne(Evento evento) {
		int pos = lista.indexOf(evento);
		System.out.println("actualizando posicion: " + pos);
		if (pos == -1)
			return 0;
		lista.set(pos, evento);
		return 1;
	}

	@Override
	public Evento findById(int idEvento) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdEvento() == idEvento)
				return lista.get(i);
		}
		return null;
	}

	@Override
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

	 public int cancelarEvento(int idEvento) {
	        Evento evento = findById(idEvento);

	        if (evento != null) {
	            // Cambiar el estado del evento a "CANCELADO"
	            evento.setEstado("CANCELADO");

	            // Actualizar el evento en la base de datos
	            updateOne(evento);

	            // Comprobar el estado por consola
	            System.out.println("Evento " + idEvento + " ha sido cancelado. Estado: " + evento.getEstado());
	        }

	        return idEvento;
	    }
}
