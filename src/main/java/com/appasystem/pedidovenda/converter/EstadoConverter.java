package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.repository.Estados;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter{

	//@Inject
	private Estados estados;
	
	public EstadoConverter() {
		estados = CDIServiceLocator.getBean(Estados.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Estado retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = estados.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Estado estado = (Estado) value;
			
			return estado.getId() == null ? null : estado.getId().toString();
		}
		
		return "";
	}

	
	
}
