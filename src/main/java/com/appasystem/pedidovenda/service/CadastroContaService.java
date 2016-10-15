package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.financeiro.Conta;
import com.appasystem.pedidovenda.repository.Contas;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroContaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Contas contas;
	
	@Transactional
	public Conta salvar(Conta conta){
		
		return contas.guardar(conta);
		
	}
	
}
