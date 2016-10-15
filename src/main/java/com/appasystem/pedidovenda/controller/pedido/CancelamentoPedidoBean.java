package com.appasystem.pedidovenda.controller.pedido;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.event.PedidoAlteradoEvent;
import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.service.CancelamentoPedidoService;
import com.appasystem.pedidovenda.util.jpa.Transactional;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@RequestScoped
@Named
public class CancelamentoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Transactional
	public void cancelarPedido(){
		this.pedido.removerItemVazio();
		
		try{
			this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
			//lançar um evento CDI
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido Cancelado com Sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}
	}
	
}
