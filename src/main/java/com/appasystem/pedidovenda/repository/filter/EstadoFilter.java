package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.model.localidade.Regiao;

public class EstadoFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String sigla;
	private String nome;
	private Regiao regiao;
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Regiao getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	
}
