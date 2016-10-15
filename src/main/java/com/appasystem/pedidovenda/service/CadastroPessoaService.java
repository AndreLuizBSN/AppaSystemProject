package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.repository.Pessoas;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroPessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;

	@Transactional
	public Pessoa salvar(Pessoa pessoa){
		
		return pessoas.guardar(pessoa);
		
	}
	
}
