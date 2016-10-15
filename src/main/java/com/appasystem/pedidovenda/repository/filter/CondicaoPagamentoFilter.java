package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

public class CondicaoPagamentoFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private int intAtivo = -1;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIntAtivo() {
		return intAtivo;
	}
	public void setIntAtivo(int intAtivo) {
		this.intAtivo = intAtivo;
	}
	
	
	
}