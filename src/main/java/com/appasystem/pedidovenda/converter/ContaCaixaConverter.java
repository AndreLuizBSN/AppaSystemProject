package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.repository.ContasCaixas;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ContaCaixa.class)
public class ContaCaixaConverter implements Converter{

	//@Inject
	private ContasCaixas contasCaixas;
	
	public ContaCaixaConverter() {
		contasCaixas = CDIServiceLocator.getBean(ContasCaixas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		ContaCaixa retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = contasCaixas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			ContaCaixa contaCaixa = (ContaCaixa) value;
			
			return contaCaixa.getId() == null ? null : contaCaixa.getId().toString();
		}
		
		return "";
	}

	
	
}
