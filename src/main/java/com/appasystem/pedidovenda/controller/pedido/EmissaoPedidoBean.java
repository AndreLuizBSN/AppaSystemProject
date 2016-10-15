package com.appasystem.pedidovenda.controller.pedido;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.event.PedidoAlteradoEvent;
import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.service.EmissaoPedidoService;
import com.appasystem.pedidovenda.util.jpa.Transactional;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

	@Transactional
	public void emitirPedido(){
		this.pedido.removerItemVazio();
		
		try{
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			//lançar um evento CDI
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido Emitido com Sucesso!");
		}finally{
			this.pedido.adicionarItemVazio();
		}
		
	}
	
}
