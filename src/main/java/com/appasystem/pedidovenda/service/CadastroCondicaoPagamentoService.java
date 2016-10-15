package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.repository.CondicoesPagamentos;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroCondicaoPagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CondicoesPagamentos condicoesPagamentos;

	@Transactional
	public CondicaoPagamento salvar(CondicaoPagamento condicaoPagamento){
		
		if(condicaoPagamento.getCondicaoPagamentoItens().isEmpty()){
			throw new NegocioException("A Condição de Pagamento deve conter pelo menos 1 dia cadastrado!");
		}
		
		return this.condicoesPagamentos.guardar(condicaoPagamento);
		
	}
	
}
