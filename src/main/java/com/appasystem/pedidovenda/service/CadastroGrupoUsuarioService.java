package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.usuario.Grupo;
import com.appasystem.pedidovenda.repository.Grupos;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroGrupoUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;

	@Transactional
	public Grupo salvar(Grupo grupo){
		
		return grupos.guardar(grupo);
		
	}
	
}
