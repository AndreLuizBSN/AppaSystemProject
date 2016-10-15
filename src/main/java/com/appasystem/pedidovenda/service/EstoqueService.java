package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pedido.ItemPedido;
import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.repository.Pedidos;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido){
		
		pedido = pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
		
	}

	@Transactional
	public void retornarItensEstoque(Pedido pedido) {
		pedido = pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().retornarEstoque(item.getQuantidade());
		}
	}
	
}
