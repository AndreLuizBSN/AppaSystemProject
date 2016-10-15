package com.appasystem.pedidovenda.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pedido_parcela")
public class ParcelaPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private BigDecimal valor;
	private int sequencia;
	private Pedido pedido;
	private boolean ajuste;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Column(nullable=false)
	public int getSequencia() {
		return sequencia;
	}
	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Column(nullable=false)
	public boolean isAjuste() {
		return ajuste;
	}
	public void setAjuste(boolean ajuste) {
		this.ajuste = ajuste;
	}
	
	
	
}
