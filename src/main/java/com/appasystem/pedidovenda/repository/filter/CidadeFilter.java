package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.model.localidade.Estado;

public class CidadeFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private Estado estado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
