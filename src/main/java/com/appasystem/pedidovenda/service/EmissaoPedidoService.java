package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.model.pedido.StatusPedido;
import com.appasystem.pedidovenda.repository.Pedidos;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Pedidos pedidos;

	public Pedido emitir(Pedido pedido){
		
		pedido = cadastroPedidoService.salvar(pedido);
		
		//Verificar se o pedido pode ser emitido ou não
		if(pedido.isNaoEmissivel()){
			throw new NegocioException("O pedido está "+pedido.getStatus().getDescricao()+"! Não pode ser emitido!");
		}
		
		//Movimentação de estoque
		this.estoqueService.baixarItensEstoque(pedido);
		
		//Altera o status do pedido para emitido
		pedido.setStatus(StatusPedido.EMITIDO);
		//Salva o pedido no banco
		pedido = pedidos.guardar(pedido);
		//retorna o pedido salvo
		return pedido;
		
		
	}
	
}
