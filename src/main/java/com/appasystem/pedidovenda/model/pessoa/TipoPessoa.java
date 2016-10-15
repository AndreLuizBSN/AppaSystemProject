package com.appasystem.pedidovenda.model.pessoa;

public enum TipoPessoa {

	FISICA("Física")
	, JURIDICA("Jurídica");
	
	private String descricao;

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
