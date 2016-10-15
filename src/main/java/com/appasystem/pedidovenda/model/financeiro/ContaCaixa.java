package com.appasystem.pedidovenda.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="conta_caixa")
public class ContaCaixa implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private BigDecimal saldo;
	private boolean ativo;
	private Date dataCadastro;
	private Date dataUltimoFechamento;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length=50, nullable=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	@Column(nullable=false)
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Column(name="data_cadastro", nullable=false)
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Column(name="data_ultimo_fechamento", nullable=false)
	public Date getDataUltimoFechamento() {
		return dataUltimoFechamento;
	}
	public void setDataUltimoFechamento(Date dataUltimoFechamento) {
		this.dataUltimoFechamento = dataUltimoFechamento;
	}
	
	@Transient
	public boolean isNovo(){
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente(){
		return !isNovo();
	}
	
}
