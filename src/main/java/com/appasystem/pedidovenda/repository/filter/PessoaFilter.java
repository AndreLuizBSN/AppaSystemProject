package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.util.string.AtivoInativoTodos;

public class PessoaFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String cnpjCpf;
	private String razaoSocial;
	private AtivoInativoTodos cliente;
	private AtivoInativoTodos fornecedor;
	private AtivoInativoTodos funcionario;
	private AtivoInativoTodos transportadora;
	private AtivoInativoTodos ativo;
	
	public String getCnpjCpf() {
		return cnpjCpf;
	}
	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public AtivoInativoTodos getCliente() {
		return cliente;
	}
	public void setCliente(AtivoInativoTodos cliente) {
		this.cliente = cliente;
	}
	public AtivoInativoTodos getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(AtivoInativoTodos fornecedor) {
		this.fornecedor = fornecedor;
	}
	public AtivoInativoTodos getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(AtivoInativoTodos funcionario) {
		this.funcionario = funcionario;
	}
	public AtivoInativoTodos getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(AtivoInativoTodos transportadora) {
		this.transportadora = transportadora;
	}
	public AtivoInativoTodos getAtivo() {
		return ativo;
	}
	public void setAtivo(AtivoInativoTodos ativo) {
		this.ativo = ativo;
	}
	
	
}
