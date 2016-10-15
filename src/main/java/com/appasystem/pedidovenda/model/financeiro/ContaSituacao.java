package com.appasystem.pedidovenda.model.financeiro;

public enum ContaSituacao {

	ABERTO("Em Aberto")
	, QUITADO("Quitado")
	, PARCIAL("Baixa Parcial")
	, CANCELADO("Cancelado");
	
	private String descricao;
	
	private ContaSituacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
