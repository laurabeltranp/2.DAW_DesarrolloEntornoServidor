package eventos.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import eventos.modelo.javabean.Tipo;

@Qualifier("tipo")
@Repository
public class TipoDaoImpl implements TipoDao {

	private List<Tipo> lista;
	private static int idAuto;
	
	static {
		idAuto=0;
	}
	public  TipoDaoImpl() {
		lista = new ArrayList<>();
		cargarLista();
	}
	
	private void cargarLista() {
		lista.add(new Tipo(1, "Graduacion"));
		lista.add(new Tipo(2, "Cumpleaños"));
		lista.add(new Tipo(3, "Boda"));
		idAuto = 3;
		
	}
	@Override
	public Tipo findById(int idTipo) {
		for (int i = 0; i < lista.size();i++) {
			if (lista.get(i).getIdTipo() == idTipo)
				return lista.get(i);
		}
		return null;
	}

	@Override
	public List<Tipo> findAll() {
		// TODO Auto-generated method stub
		return lista;
	}

}
