package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.model.pedido.StatusPedido;
import com.appasystem.pedidovenda.repository.Pedidos;

public class CancelamentoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Pedidos pedidos;

	public Pedido cancelar(Pedido pedido){
		
		pedido = cadastroPedidoService.salvar(pedido);
		
		//Verificar se o pedido pode ser emitido ou não
		if(pedido.isNaoCancelavel()){
			throw new NegocioException("O pedido não pode ser cancelado, pois já está "+pedido.getStatus().getDescricao()+"!");
		}
		
		//Somente retornar o estoque para pedidos emitidos
		if(pedido.isEmitido()){
			//Movimentação de estoque
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		//Altera o status do pedido para cancelado
		pedido.setStatus(StatusPedido.CANCELADO);
		//Salva o pedido no banco
		pedido = pedidos.guardar(pedido);
		//retorna o pedido salvo
		return pedido;
	}
	
}
