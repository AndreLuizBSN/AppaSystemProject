package com.appasystem.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.repository.Estados;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroEstadoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Estados estados;

	@Transactional
	public Estado salvar(Estado estado){
		Estado estadoExistente = estados.porSigla(estado.getSigla());
		
		if (estadoExistente != null && !estadoExistente.equals(estado)) {
			throw new NegocioException("Já existe um estado com a sigla informada.");
		}
		return estados.guardar(estado);
		
	}
	
}
