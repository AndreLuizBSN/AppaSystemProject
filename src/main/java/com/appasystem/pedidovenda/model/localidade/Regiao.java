package com.appasystem.pedidovenda.model.localidade;

public enum Regiao {
	
	SUL("Sul")
	, NORTE("Norte")
	, SUDESTE("Sudeste")
	, CENTROESTE("Centroeste")
	, NORDESTE("Nordeste");
	
	private String descricao;
	
	private Regiao(String descrico){
		this.descricao = descrico;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
