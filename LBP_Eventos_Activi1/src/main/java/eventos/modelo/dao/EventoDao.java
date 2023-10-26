package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.javabean.Evento;


public interface EventoDao {
	int insert(Evento evento);
	int delete(int idEvento);
	int updateOne(Evento evento);
	Evento findById(int idEvento);
	List<Evento> findAll();
	int cancelarEvento (int idEvento);
}
