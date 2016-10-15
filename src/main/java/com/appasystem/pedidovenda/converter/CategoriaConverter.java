package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.repository.Categorias;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

	//@Inject
	private Categorias categorias;
	
	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Categoria retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Categoria categoria = (Categoria) value;
			
			return categoria.getId() == null ? null : categoria.getId().toString();
		}
		
		return "";
	}

	
	
}
