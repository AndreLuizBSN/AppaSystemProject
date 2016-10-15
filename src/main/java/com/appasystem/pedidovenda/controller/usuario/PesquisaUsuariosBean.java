package com.appasystem.pedidovenda.controller.usuario;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.repository.Usuarios;
import com.appasystem.pedidovenda.repository.filter.UsuarioFilter;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Usuario usuarioSelecionado;
	
	public PesquisaUsuariosBean(){
		this.filtro = new UsuarioFilter();
	}
	
	public void excluir(Long id){
		
		usuarioSelecionado = usuarios.porId(id);
		
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuáio "+usuarioSelecionado.getId()+" - "+usuarioSelecionado.getNome()+" excluído com sucesso!");
		
	}
	
	public void pesquisar(){
		usuariosFiltrados = usuarios.filtrados(this.filtro);
	}
		
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados ;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
}