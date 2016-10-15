package com.appasystem.pedidovenda.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {
	
	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario(){
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if(usuarioLogado != null){
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}

	private UsuarioSistema getUsuarioLogado() {
		
		UsuarioSistema user = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if(auth != null && auth.getPrincipal() != null){
			user = (UsuarioSistema) auth.getPrincipal();
		}
		
		return user;
	}
	
	//Bloqueio para nao permitir emitir
	public boolean isEmitirPedidoPermitir(){
		return externalContext.isUserInRole("ADMINISTRADORES")
				||externalContext.isUserInRole("VENDEDORES");
	}
	
	//Bloqueio para nao permitir emitir
	public boolean isCancelarPedidoPermitir(){
		return externalContext.isUserInRole("ADMINISTRADORES");
	}
		
	
}
