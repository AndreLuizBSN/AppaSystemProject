package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.model.financeiro.ContaSituacao;
import com.appasystem.pedidovenda.model.financeiro.EntradaSaida;

public class ContaFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String numero;
	private EntradaSaida entradaSaida;
	private ContaSituacao[] situacaos;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public EntradaSaida getEntradaSaida() {
		return entradaSaida;
	}
	public void setEntradaSaida(EntradaSaida entradaSaida) {
		this.entradaSaida = entradaSaida;
	}
	
	public ContaSituacao[] getSituacaos() {
		return situacaos;
	}
	public void setSituacaos(ContaSituacao[] situacaos) {
		this.situacaos = situacaos;
	}
	
}