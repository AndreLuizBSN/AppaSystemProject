package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.repository.Categorias;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;

	@Transactional
	public Categoria salvar(Categoria categoria){
		
		return categorias.guardar(categoria);
		
	}
	
}
