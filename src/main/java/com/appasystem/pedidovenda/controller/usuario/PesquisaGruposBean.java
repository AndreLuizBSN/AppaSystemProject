package com.appasystem.pedidovenda.controller.usuario;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.usuario.Grupo;
import com.appasystem.pedidovenda.repository.Grupos;
import com.appasystem.pedidovenda.repository.filter.GrupoFilter;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaGruposBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GrupoFilter filtro;
	private List<Grupo> gruposFiltrados;
	
	@Inject
	private Grupos grupos;
	
	@Inject
	private Grupo grupoSelecionado;
	
	public PesquisaGruposBean(){
		this.filtro = new GrupoFilter();
	}
	
	public void excluir(Long id){
		
		grupoSelecionado = grupos.porId(id);
		
		grupos.remover(grupoSelecionado);
		gruposFiltrados.remove(grupoSelecionado);
		
		FacesUtil.addInfoMessage("Grupo "+grupoSelecionado.getId()+" - "+grupoSelecionado.getNome()+" excluído com sucesso!");
		
	}
	
	public void pesquisar(){
		gruposFiltrados = grupos.filtrados(this.filtro);
	}
		
	public List<Grupo> getGruposFiltrados() {
		return gruposFiltrados ;
	}

	public GrupoFilter getFiltro() {
		return filtro;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}
	
}