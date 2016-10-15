package com.appasystem.pedidovenda.controller.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.repository.Categorias;
import com.appasystem.pedidovenda.service.CadastroProdutoService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	public CadastroProdutoBean() {
		limpar();
	}
	
	public void inicializar() {
		
		if(FacesUtil.isNotPostback()){
			categoriasRaizes = categorias.raizes(); 
		}
		
		if(this.categoriaPai != null){
			carregarSubcategorias();
		}
		
	}
	
	public void carregarSubcategorias(){
		subcategorias = categorias.subCategoriasDe(categoriaPai);
	}
	
	//limpar o formulário
	private void limpar(){
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}
		
	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		FacesUtil.addInfoMessage("Poduto salvo com sucesso!");
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto != null){
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
		
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
	
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public boolean isEditando(){
		return this.produto.getId() != null;
	}
	
}