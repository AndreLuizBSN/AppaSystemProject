package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.repository.Modulos;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroModuloService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Modulos modulos;

	@Transactional
	public Modulo salvar(Modulo modulo){
		
		return modulos.guardar(modulo);
		
	}
	
}
