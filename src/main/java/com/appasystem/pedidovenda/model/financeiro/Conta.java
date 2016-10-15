package com.appasystem.pedidovenda.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.appasystem.pedidovenda.date.AnoClass;
import com.appasystem.pedidovenda.date.Meses;
import com.appasystem.pedidovenda.date.MesesClass;
import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.model.pedido.FormaPagamento;
import com.appasystem.pedidovenda.util.number.Money;

@Entity
@Table(name="conta")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String numero;
	private int parcela = 1;
	private String numeroParcela;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal valorRestante = BigDecimal.ZERO;
	private BigDecimal valorDescontoAcrescimo = BigDecimal.ZERO;
	private Date dataCadastro;
	private Date dataVencimento;
	private Meses mes = new MesesClass().getMesAtual();
	private int anoBase = new AnoClass().getAnoAtual();
	private FormaPagamento formaPagamento;
	private ContaSituacao situacao;
	private EntradaSaida entradaSaida;
	private ContaCaixa contaCaixa;
	private Cliente pessoa;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false, length=15)
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Column(nullable=false)
	public int getParcela() {
		return parcela;
	}
	public void setParcela(int parcela) {
		this.parcela = parcela;
	}
	
	@Column(nullable=false, name="numero_parcela", length=35)
	public String getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	
	@Column(nullable=false, name="valor_total", precision=10, scale=2)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Column(nullable=false, name="valor_restante", precision=10, scale=2)
	public BigDecimal getValorRestante() {
		return valorRestante;
	}
	public void setValorRestante(BigDecimal valorRestante) {
		this.valorRestante = valorRestante;
	}
	
	@Column(nullable=false, name="valor_desconto_acrescimo", precision=10, scale=2)
	public BigDecimal getValorDescontoAcrescimo() {
		return valorDescontoAcrescimo;
	}
	public void setValorDescontoAcrescimo(BigDecimal valorDescontoAcrescimo) {
		this.valorDescontoAcrescimo = valorDescontoAcrescimo;
	}
	
	@Column(nullable=false, name="data_cadastro")
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	@Column(nullable=false, name="data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=30)
	public Meses getMes() {
		return mes;
	}
	public void setMes(Meses mes) {
		this.mes = mes;
	}
	
	@Column(nullable=false)
	public int getAnoBase() {
		return anoBase;
	}
	public void setAnoBase(int anoBase) {
		this.anoBase = anoBase;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, name="forma_pagamento", length=30)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, name="situacao", length=30)
	public ContaSituacao getSituacao() {
		return situacao;
	}
	public void setSituacao(ContaSituacao situacao) {
		this.situacao = situacao;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, name="entrada_saida", length=30)
	public EntradaSaida getEntradaSaida() {
		return entradaSaida;
	}
	public void setEntradaSaida(EntradaSaida entradaSaida) {
		this.entradaSaida = entradaSaida;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_caixa_id", nullable = false)
	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}
	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	public Cliente getPessoa() {
		return pessoa;
	}
	public void setPessoa(Cliente pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public void recalcularValorRestante() {
		
		Money money = new Money();
		
		this.setValorRestante(
				BigDecimal.valueOf(
						money.converterMoney(
								this.getValorTotal()
						)
						+ 
						money.converterMoney(
								this.getValorDescontoAcrescimo()
						)
				)
		);
		
		//Falta calcular o restante das baixas
		
	}
	
	
}
