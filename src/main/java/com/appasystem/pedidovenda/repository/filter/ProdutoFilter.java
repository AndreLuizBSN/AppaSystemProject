package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.validation.SKU;


public class ProdutoFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String sku;
	private String nome;
	private int filtroRapido = -1;
	
	@SKU
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getFiltroRapido() {
		return filtroRapido;
	}
	public void setFiltroRapido(int filtroRapido) {
		this.filtroRapido = filtroRapido;
	}
	
	
}
