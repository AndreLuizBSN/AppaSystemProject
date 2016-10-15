package com.appasystem.pedidovenda.controller.produto;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.repository.Categorias;
import com.appasystem.pedidovenda.repository.filter.CategoriaFilter;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CategoriaFilter filtro;
	private List<Categoria> categoriasFiltradas;
	
	@Inject
	private Categorias categorias;
	
	@Inject
	private Categoria categoriaSelecionada;
	
	public PesquisaCategoriasBean(){
		this.filtro = new CategoriaFilter();
	}
	
	public void excluir(Long id){
		
		categoriaSelecionada = categorias.porId(id);
		
		categorias.remover(categoriaSelecionada);
		categoriasFiltradas.remove(categoriaSelecionada);
		
		FacesUtil.addInfoMessage("Categoria "+categoriaSelecionada.getId()+" - "+categoriaSelecionada.getDescricao()+" excluída com sucesso!");
		
	}
	
	public void pesquisar(){
		categoriasFiltradas = categorias.filtrados(this.filtro);
	}
		
	public List<Categoria> getCategoriasFiltradas() {
		return categoriasFiltradas;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}
	
}