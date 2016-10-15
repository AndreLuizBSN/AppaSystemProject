package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.repository.CondicoesPagamentos;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = CondicaoPagamento.class)
public class CondicaoPagamentoConverter implements Converter{

	//@Inject
	private CondicoesPagamentos condicoesPagamentos;
	
	public CondicaoPagamentoConverter() {
		condicoesPagamentos = CDIServiceLocator.getBean(CondicoesPagamentos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		CondicaoPagamento retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = condicoesPagamentos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			CondicaoPagamento condicaoPagamento = (CondicaoPagamento) value;
			
			return condicaoPagamento.getId() == null ? null : condicaoPagamento.getId().toString();
		}
		
		return "";
	}

	
	
}
