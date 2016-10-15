package com.appasystem.pedidovenda.controller.localidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.repository.Cidades;
import com.appasystem.pedidovenda.repository.filter.CidadeFilter;

@ViewScoped
@Named
public class PesquisaCidadesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;
	
	private CidadeFilter filtro;
	private List<Cidade> cidadesFiltrados;
	
	public PesquisaCidadesBean() {
		filtro = new CidadeFilter();
		cidadesFiltrados = new ArrayList<Cidade>();
	}

	//pesquisa
	public void pesquisar(){
		cidadesFiltrados = cidades.filtrados(filtro);
	}
	
	public List<Cidade> getCidadesFiltrados() {
		return cidadesFiltrados;
	}

	public CidadeFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CidadeFilter filtro) {
		this.filtro = filtro;
	}

}
