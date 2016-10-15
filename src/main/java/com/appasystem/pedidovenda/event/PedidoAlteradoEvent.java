package com.appasystem.pedidovenda.event;

import com.appasystem.pedidovenda.model.pedido.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido){
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}
