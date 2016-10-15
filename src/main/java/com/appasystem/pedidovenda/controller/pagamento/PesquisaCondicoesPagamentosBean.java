package com.appasystem.pedidovenda.controller.pagamento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.repository.CondicoesPagamentos;
import com.appasystem.pedidovenda.repository.filter.CondicaoPagamentoFilter;

@Named
@ViewScoped
public class PesquisaCondicoesPagamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CondicaoPagamentoFilter filtro;
	private List<CondicaoPagamento> condicoesPagamentosFiltrados;
	
	@Inject
	private CondicoesPagamentos condicoesPagamentos;
	
	public PesquisaCondicoesPagamentosBean(){
		this.filtro = new CondicaoPagamentoFilter();
	}
	
	public void pesquisar(){
		
		condicoesPagamentosFiltrados = condicoesPagamentos.filtrados(this.filtro);
		
	}
		
	public List<CondicaoPagamento> getCondicoesPagamentosFiltrados() {
		return condicoesPagamentosFiltrados;
	}

	public CondicaoPagamentoFilter getFiltro() {
		return filtro;
	}

}