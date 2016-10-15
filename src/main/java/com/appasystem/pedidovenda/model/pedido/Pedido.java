package com.appasystem.pedidovenda.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.model.usuario.Usuario;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCriacao;
	private String observacao;
	private Date dataEntrega;
	private BigDecimal valorFrete = BigDecimal.ZERO;
	private BigDecimal valorDesconto = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private StatusPedido status = StatusPedido.ORCAMENTO;
	private FormaPagamento formaPagamento;
	private Usuario vendedor;
	private Cliente cliente;
	private EnderecoEntrega enderecoEntrega;
	private CondicaoPagamento condicaoPagamento;
	private Date dataBaseParcela;
	private List<ItemPedido> itens = new ArrayList<>();
	private List<ParcelaPedido> parcelas = new ArrayList<ParcelaPedido>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@NotNull
	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	@NotNull
	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@NotNull
	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "condicao_pagamento_id", nullable = false)
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_base_parcela", nullable = false)
	public Date getDataBaseParcela() {
		return dataBaseParcela;
	}

	public void setDataBaseParcela(Date dataBaseParcela) {
		this.dataBaseParcela = dataBaseParcela;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 20)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Embedded
	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ParcelaPedido> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaPedido> parcelas) {
		this.parcelas = parcelas;
	}

	@Transient
	public boolean isNovo(){
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente(){
		return !isNovo();
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Transient
	public BigDecimal getValorSubtotal(){
		return this.getValorTotal().add(this.getValorDesconto()).subtract(this.getValorFrete());
	}

	public void recalcularValorTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		
		total = total.add(this.getValorFrete());
		total = total.subtract(this.getValorDesconto());
		
		for(ItemPedido item : this.getItens()){
			
			if(item.getProduto() != null && item.getProduto().getId() != null){
				total = total.add(item.getValorTotal());
			}
			
		}
		
		this.setValorTotal(total);
		
	}

	public void adicionarItemVazio() {
		
		if(this.isOrcamento()){
			
			Produto produto = new Produto();
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(1);
			itemPedido.setPedido(this);
			
			this.getItens().add(0,itemPedido);
		}
	}

	@Transient
	public boolean isOrcamento() {
		return StatusPedido.ORCAMENTO.equals(this.getStatus());
	}

	public void removerItemVazio() {
		ItemPedido primeiroItem = getItens().get(0);
		
		if(primeiroItem != null && primeiroItem.getProduto().getId() == null){
			this.getItens().remove(0);
		}
		
	}

	@Transient
	public boolean isValorTotalNegativo() {
		return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
	}

	@Transient
	public boolean isEmitido() {
		return StatusPedido.EMITIDO.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	private boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}

	@Transient
	public boolean isCancelavel(){
		return !this.getStatus().equals(StatusPedido.CANCELADO);
	}
	
	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}

	@Transient
	public boolean isEditavel(){
		return this.isOrcamento();
	}
	
	@Transient
	public boolean isNaoEditavel(){
		return !this.isEditavel();
	}
}