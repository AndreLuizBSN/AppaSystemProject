package com.appasystem.pedidovenda.model.financeiro;

public enum EntradaSaida {

	ENTRADA("Entrada")
	, SAIDA("Saída");
	
	private String descricao;
	
	private EntradaSaida(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
