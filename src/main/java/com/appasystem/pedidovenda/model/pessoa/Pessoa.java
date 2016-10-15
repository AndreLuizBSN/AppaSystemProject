package com.appasystem.pedidovenda.model.pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String razaoSocial;
	private String nomeFantasia;
	private TipoPessoa tipoPessoa;
	private String cnpjCpf;
	private String rgIe;
	private boolean cliente;
	private boolean fornecedor;
	private boolean funcionario;
	private boolean transportadora;
	private boolean ativo = true;
	private List<PessoaEndereco> enderecos = new ArrayList<>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable = false, length = 200, name="razao_social")
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@Column(nullable = false, length = 200, name="nome_fantasia")
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	@Column(nullable = false, length = 18, name="cnpj_cpf")
	public String getCnpjCpf() {
		return cnpjCpf;
	}
	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}
	
	@Column(nullable = false, length = 10, name="rg_ie")
	public String getRgIe() {
		return rgIe;
	}
	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}
	
	@NotNull
	public boolean isCliente() {
		return cliente;
	}
	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
	
	@NotNull
	public boolean isFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@NotNull
	public boolean isFuncionario() {
		return funcionario;
	}
	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}
	
	@NotNull
	public boolean isTransportadora() {
		return transportadora;
	}
	public void setTransportadora(boolean transportadora) {
		this.transportadora = transportadora;
	}
	
	@NotNull
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	public List<PessoaEndereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<PessoaEndereco> enderecos) {
		this.enderecos = enderecos;
	}
	
}
