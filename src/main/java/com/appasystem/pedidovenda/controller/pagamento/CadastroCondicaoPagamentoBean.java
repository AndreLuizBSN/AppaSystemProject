package com.appasystem.pedidovenda.controller.pagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamentoItens;
import com.appasystem.pedidovenda.service.CadastroCondicaoPagamentoService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCondicaoPagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CondicaoPagamento condicaoPagamento;
	
	@Inject
	private CadastroCondicaoPagamentoService cadastroCondicaoPagamentoService;
	
	private List<CondicaoPagamentoItens> itens;
	
	public CadastroCondicaoPagamentoBean() {
		limpar();
		itens = new ArrayList<CondicaoPagamentoItens>();
	}
	
	private void limpar(){
		condicaoPagamento = new CondicaoPagamento();
	}
	
	public void salvar() {
		
		for (CondicaoPagamentoItens item : itens) {
			if(item.getCondicaoPagamento() == null){
				item.setCondicaoPagamento(condicaoPagamento);
			}
		}
		
		this.condicaoPagamento.setCondicaoPagamentoItens(itens);
		this.condicaoPagamento = cadastroCondicaoPagamentoService.salvar(this.condicaoPagamento);
		FacesUtil.addInfoMessage("Condicão de Pagamento salvo com Sucesso!");
		
	}
	
	public void inicializar(){
		
		if(condicaoPagamento.getId() != null){
			if(!condicaoPagamento.getCondicaoPagamentoItens().isEmpty()){
				itens = condicaoPagamento.getCondicaoPagamentoItens();
			}
		}
	}
	
	public void AdicionarLinha(){
		if(this.condicaoPagamento.getId() == null){
			CondicaoPagamentoItens item = new CondicaoPagamentoItens();
			item.setCondicaoPagamento(null);
			item.setDias(0);
			item.setParcela(0);
			itens.add(item);
		}else{
			this.condicaoPagamento.adicionarItemVazio();
		}
	}
	
	public void removerLinha(int linha){
		this.itens.remove(linha);
	}
	
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}
	
	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public boolean isEditando(){
		return this.condicaoPagamento.getId() != null;
	}

	public List<CondicaoPagamentoItens> getItens() {
		return itens;
	}

	public void setItens(List<CondicaoPagamentoItens> itens) {
		this.itens = itens;
	}

}