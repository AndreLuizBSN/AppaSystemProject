package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.financeiro.Conta;
import com.appasystem.pedidovenda.repository.Contas;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter{

	//@Inject
	private Contas contas;
	
	public ContaConverter() {
		contas = CDIServiceLocator.getBean(Contas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Conta retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = contas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Conta conta = (Conta) value;
			
			return conta.getId() == null ? null : conta.getId().toString();
		}
		
		return "";
	}

	
	
}
