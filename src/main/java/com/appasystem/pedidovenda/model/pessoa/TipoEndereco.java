package com.appasystem.pedidovenda.model.pessoa;

public enum TipoEndereco {

	RESIDENCIAL("Redidencial")
	, ENTREGA("Entrega");
	
	private String descricao;
	
	private TipoEndereco(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
