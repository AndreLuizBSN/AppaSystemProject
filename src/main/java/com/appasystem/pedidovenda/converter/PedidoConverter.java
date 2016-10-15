package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.repository.Pedidos;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter{

	//@Inject
	private Pedidos pedidos;
	
	public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Pedido retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = pedidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Pedido pedido = (Pedido) value;
			
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

	
	
}
