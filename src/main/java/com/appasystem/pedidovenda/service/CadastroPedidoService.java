package com.appasystem.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.model.pedido.StatusPedido;
import com.appasystem.pedidovenda.repository.Pedidos;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;

	@Transactional
	public Pedido salvar(Pedido pedido){
		
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.recalcularValorTotal();
		
		if(pedido.getItens().isEmpty()){
			throw new NegocioException("O pedido deve conter pelo menos 1 item!");
		}
		
		if(pedido.isValorTotalNegativo()){
			throw new NegocioException("O valor do pedido não pode ser negativo");
		}
		
		return this.pedidos.guardar(pedido);
		
	}
	
}
