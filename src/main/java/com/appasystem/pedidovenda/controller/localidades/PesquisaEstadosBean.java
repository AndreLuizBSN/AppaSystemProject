package com.appasystem.pedidovenda.controller.localidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.model.localidade.Regiao;
import com.appasystem.pedidovenda.repository.Estados;
import com.appasystem.pedidovenda.repository.filter.EstadoFilter;

@ViewScoped
@Named
public class PesquisaEstadosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Estados estados;
	
	private EstadoFilter filtro;
	private List<Estado> estadosFiltrados;
	
	public PesquisaEstadosBean() {
		filtro = new EstadoFilter();
		estadosFiltrados = new ArrayList<Estado>();
	}

	//pesquisa
	public void pesquisar(){
		estadosFiltrados = estados.filtrados(filtro);
	}
	
	public Regiao[] regioes(){
		return Regiao.values();
	}
	
	public List<Estado> getEstadosFiltrados() {
		return estadosFiltrados;
	}

	public EstadoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EstadoFilter filtro) {
		this.filtro = filtro;
	}
	
}
