package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.repository.Produtos;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter{

	//@Inject
	private Produtos produtos;
	
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(Produtos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Produto retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Produto produto = (Produto) value;
			
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

	
	
}
