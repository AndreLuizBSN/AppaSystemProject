package com.appasystem.pedidovenda.controller.usuario;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.service.CadastroUsuarioService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	/*@Inject
	private Usuarios usuarios;
	*/
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	public CadastroUsuarioBean() {
		limpar();
	}
	
	public void inicializar() {
		
	}
	
	//limpar o formulário
	private void limpar(){
		usuario = new Usuario();
	}
		
	public void salvar() {
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		FacesUtil.addInfoMessage("Usuáio salvo com sucesso!");
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isEditando(){
		return this.usuario.getId() != null;
	}

}