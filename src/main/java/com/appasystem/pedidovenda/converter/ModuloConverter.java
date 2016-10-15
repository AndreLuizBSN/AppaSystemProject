package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.repository.Modulos;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Modulo.class)
public class ModuloConverter implements Converter{

	//@Inject
	private Modulos modulos;
	
	public ModuloConverter() {
		modulos = CDIServiceLocator.getBean(Modulos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Modulo retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = modulos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Modulo modulo = (Modulo) value;
			
			return modulo.getId() == null ? null : modulo.getId().toString();
		}
		
		return "";
	}

	
	
}
