package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.repository.Clientes;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter{

	//@Inject
	private Clientes clientes;
	
	public ClienteConverter() {
		clientes = CDIServiceLocator.getBean(Clientes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Cliente retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = clientes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			return ((Cliente) value).getId().toString();
		}
		
		return "";
	}

	
	
}
