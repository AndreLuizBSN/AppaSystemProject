package com.appasystem.pedidovenda.controller.produto;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.repository.Categorias;
import com.appasystem.pedidovenda.service.CadastroCategoriaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Categoria categoria;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	
	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;
	
	public CadastroCategoriaBean() {
		limpar();
	}
	
	public void inicializar() {
		
		if(FacesUtil.isNotPostback()){
			categoriasRaizes = categorias.raizes();
		}
	}
	
	//limpar o formulário
	private void limpar(){
		categoria = new Categoria();
		categoriaPai = null;
	}
		
	public void salvar() {
		this.categoria = cadastroCategoriaService.salvar(this.categoria);
		FacesUtil.addInfoMessage("Categoria salva com sucesso!");
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
		if(this.categoria != null){
			this.categoriaPai = this.categoria.getCategoriaPai();
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

	public boolean isEditando(){
		return this.categoria.getId() != null;
	}
}