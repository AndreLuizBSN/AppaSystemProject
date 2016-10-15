package com.appasystem.pedidovenda.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.financeiro.Conta;
import com.appasystem.pedidovenda.model.financeiro.ContaSituacao;
import com.appasystem.pedidovenda.model.financeiro.EntradaSaida;
import com.appasystem.pedidovenda.repository.Contas;
import com.appasystem.pedidovenda.repository.filter.ContaFilter;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaContaPagarBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContaFilter filtro;
	private List<Conta> contasFiltrados;
	
	@Inject
	private Contas contas;
	
	@Inject
	private Conta contaSelecionado;
	
	//@Inject
	//private CadastroContaCaixaService cadastroContaCaixaService;
	
	public PesquisaContaPagarBean(){
		this.filtro = new ContaFilter();
	}
	
	
	public void excluir(String id){
		
		Long intId = Long.parseLong(id);
		
		contaSelecionado = contas.porId(intId);
		
		contas.remover(contaSelecionado);
		contasFiltrados.remove(contaSelecionado);
		
		FacesUtil.addInfoMessage("Conta "+contaSelecionado.getNumero()+" - "+contaSelecionado.getParcela()+" excluída com sucesso!");
		
	}
	
	
	public void pesquisar(){
		
		this.filtro.setEntradaSaida(EntradaSaida.SAIDA);
		
		for (ContaSituacao contaSituacao : this.filtro.getSituacaos()) {
			System.out.println(contaSituacao.getDescricao());
		}
		
		contasFiltrados = contas.filtrados(this.filtro);
	}
	
	public ContaSituacao[] getSituacaos(){
		return ContaSituacao.values();
	}
	
	public List<Conta> getContasFiltrados() {
		return contasFiltrados;
	}

	public ContaFilter getFiltro() {
		return filtro;
	}

	public Conta getContaSelecionado() {
		return contaSelecionado;
	}

	public void setContaSelecionado(Conta contaSelecionado) {
		this.contaSelecionado = contaSelecionado;
	}

}