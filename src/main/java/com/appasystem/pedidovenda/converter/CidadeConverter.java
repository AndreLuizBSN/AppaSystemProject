package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.repository.Cidades;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter{

	//@Inject
	private Cidades cidades;
	
	public CidadeConverter() {
		cidades = CDIServiceLocator.getBean(Cidades.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Cidade retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = cidades.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Cidade cidade = (Cidade) value;
			
			return cidade.getId() == null ? null : cidade.getId().toString();
		}
		
		return "";
	}

	
	
}
