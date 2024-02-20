package com.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidos.modelo.entities.Comercial;
import com.pedidos.respository.ComercialRepository;

@Service
public class ComercialServiceImplMy8 implements ComercialService {

	@Autowired
	private ComercialRepository comercialRepository;
	
	@Override
	public Comercial altaComercial(Comercial comercial) {
		try {
			return comercialRepository.save(comercial);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean eliminarUno(int idComercial) {
		try {
			if (buscarUno(idComercial) != null) {
				 comercialRepository.deleteById(idComercial);
				 return true;
			}
			else
				return false;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Comercial buscarUno(int idComercial) {
		// TODO Auto-generated method stub
		return comercialRepository.findById(idComercial).orElse(null);
	}

	@Override
	public List<Comercial> buscarPorCliente(int idCliente) {
		// TODO Auto-generated method stub
		return comercialRepository.buscarPorCliente(idCliente);
	}

	@Override
	public List<Comercial> bucarComercialesConPedido() {
		// TODO Auto-generated method stub
		return comercialRepository.buscarComercialesConPedidos();
	}

}
