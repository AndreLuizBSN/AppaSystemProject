package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.repository.Cidades;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroCidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cidades cidades;

	@Transactional
	public Cidade salvar(Cidade cidade){
		return cidades.guardar(cidade);
		
	}
	
}
