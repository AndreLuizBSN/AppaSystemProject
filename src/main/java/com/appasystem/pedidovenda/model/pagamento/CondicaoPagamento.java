package com.appasystem.pedidovenda.model.pagamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "condicao_pagamento")
public class CondicaoPagamento {

	private Long id;
	private String nome;
	private boolean ativo;
	private List<CondicaoPagamentoItens> condicaoPagamentoItens = new ArrayList<CondicaoPagamentoItens>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, length = 150)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(nullable = false)
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@OneToMany(mappedBy = "condicaoPagamento", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CondicaoPagamentoItens> getCondicaoPagamentoItens() {
		return condicaoPagamentoItens;
	}
	public void setCondicaoPagamentoItens(
			List<CondicaoPagamentoItens> condicaoPagamentoItens) {
		this.condicaoPagamentoItens = condicaoPagamentoItens;
	}
	
	public void removerItemVazio() {
		CondicaoPagamentoItens primeiroItem = getCondicaoPagamentoItens().get(0);
		
		if(primeiroItem != null && primeiroItem.getId() == null){
			this.getCondicaoPagamentoItens().remove(0);
		}
			
	}
	public void adicionarItemVazio() {
		
		CondicaoPagamentoItens item = new CondicaoPagamentoItens();
		item.setDias(0);
		item.setParcela(0);
		item.setCondicaoPagamento(this);
		this.getCondicaoPagamentoItens().add(item);
	}
	
	
	
}
