package com.appasystem.pedidovenda.controller.produto;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.repository.Produtos;
import com.appasystem.pedidovenda.repository.filter.ProdutoFilter;
import com.appasystem.pedidovenda.service.CadastroProdutoService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	private int filtroRapido;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private Produto produtoSelecionado;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	public PesquisaProdutosBean(){
		this.filtro = new ProdutoFilter();
	}
	
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
	
	public void pesquisar(){
		produtosFiltrados = produtos.filtrados(this.filtro);

		/*Pegar a URL completa
    	#{request.getRequestURL())}
    	*/
	}
		
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados ;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public int getFiltroRapido() {
		return filtroRapido;
	}

	public void setFiltroRapido(int filtroRapido) {
		this.filtroRapido = filtroRapido;
	}
	
}