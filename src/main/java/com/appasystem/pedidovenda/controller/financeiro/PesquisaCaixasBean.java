package com.appasystem.pedidovenda.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.repository.ContasCaixas;
import com.appasystem.pedidovenda.repository.filter.ContaCaixaFilter;

@Named
@ViewScoped
public class PesquisaCaixasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContaCaixaFilter filtro;
	private List<ContaCaixa> contasCaixaFiltrados;
	
	@Inject
	private ContasCaixas contasCaixas;
	
	@Inject
	private ContaCaixa contaCaixaSelecionado;
	
	//@Inject
	//private CadastroContaCaixaService cadastroContaCaixaService;
	
	public PesquisaCaixasBean(){
		this.filtro = new ContaCaixaFilter();
	}
	
	/*
	public void excluir(String sku){
		
		produtoSelecionado = produtos.porSku(sku);
		
		produtos.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		
		FacesUtil.addInfoMessage("Produto "+produtoSelecionado.getSku()+" - "+produtoSelecionado.getNome()+" excluído com sucesso!");
		
	}
	
	public void ativarDesativar(String sku, boolean ativo){
		
		//Inverter
		if(ativo){
			ativo = false;
		}else{
			ativo = true;
		}
		
		produtoSelecionado = produtos.porSku(sku);
		produtoSelecionado.setAtivo(ativo);
		produtoSelecionado = cadastroProdutoService.salvar(produtoSelecionado);
		String texto;
		if(ativo){
			texto = "ativado";
		}else{
			texto = "inativado";
		}
		FacesUtil.addInfoMessage("Poduto "+produtoSelecionado.getSku()+" - "+produtoSelecionado.getNome()+" "+texto+" com sucesso!");
		
	}
	
	*/
	
	public void pesquisar(){
		
		contasCaixaFiltrados = contasCaixas.filtrados(this.filtro);
	}
	
	public List<ContaCaixa> getContasCaixaFiltrados() {
		return contasCaixaFiltrados;
	}

	public ContaCaixaFilter getFiltro() {
		return filtro;
	}

	public ContaCaixa getContaCaixaSelecionado() {
		return contaCaixaSelecionado;
	}

	public void setContaCaixaSelecionado(ContaCaixa contaCaixaSelecionado) {
		this.contaCaixaSelecionado = contaCaixaSelecionado;
	}
	

}