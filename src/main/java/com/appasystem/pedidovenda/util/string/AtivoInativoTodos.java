package com.appasystem.pedidovenda.util.string;

public enum AtivoInativoTodos {

	TODOS("Todos")
	, ATIVO("Ativo")
	, INATIVO("Inativo");
	
	private String descricao;

	private AtivoInativoTodos(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
