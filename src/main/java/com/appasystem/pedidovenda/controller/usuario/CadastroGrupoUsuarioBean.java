package com.appasystem.pedidovenda.controller.usuario;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.usuario.Grupo;
import com.appasystem.pedidovenda.repository.Grupos;
import com.appasystem.pedidovenda.service.CadastroGrupoUsuarioService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroGrupoUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Grupo grupo;
	private List<Grupo> gruposRaizes;
	private Long id = new Long(0);
	
	@Inject
	private Grupos grupos;
	
	@Inject
	private CadastroGrupoUsuarioService cadastroGrupoUsuarioService;
	
	public CadastroGrupoUsuarioBean() {
		limpar();
	}
	
	public void inicializar() {
		
		if(this.id != 0){
			this.grupo = grupos.porId(id);
		}
		
		
		
		/*
		if(FacesUtil.isNotPostback()){
			gruposRaizes = grupos.raizes();
		}*/
	}
	
	//limpar o formulário
	private void limpar(){
		grupo = new Grupo();
	}
		
	public void salvar() {
		this.grupo = cadastroGrupoUsuarioService.salvar(this.grupo);
		FacesUtil.addInfoMessage("Grupo de Usuário salvo com sucesso!");
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}

	public boolean isEditando(){
		return this.grupo.getId() != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}