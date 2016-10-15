package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.repository.Pessoas;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter{

	//@Inject
	private Pessoas pessoas;
	
	public PessoaConverter() {
		pessoas = CDIServiceLocator.getBean(Pessoas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Pessoa retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = pessoas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Pessoa pessoa = (Pessoa) value;
			
			return pessoa.getId() == null ? null : pessoa.getId().toString();
		}
		
		return "";
	}

	
	
}
