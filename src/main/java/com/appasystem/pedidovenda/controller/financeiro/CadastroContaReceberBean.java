package com.appasystem.pedidovenda.controller.financeiro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.date.Meses;
import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.model.financeiro.Conta;
import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.model.financeiro.ContaSituacao;
import com.appasystem.pedidovenda.model.financeiro.EntradaSaida;
import com.appasystem.pedidovenda.model.pedido.FormaPagamento;
import com.appasystem.pedidovenda.repository.Clientes;
import com.appasystem.pedidovenda.repository.ContasCaixas;
import com.appasystem.pedidovenda.service.CadastroContaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroContaReceberBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Conta conta;
	
	@Inject
	private CadastroContaService cadastroContaService;

	@Inject
	private ContasCaixas contasCaixas;
	
	@Inject
	private Clientes clientes;
	
	public CadastroContaReceberBean() {
		limpar();
	}
	
	public void inicializar() {
		
	}
	
	//limpar o formulário
	private void limpar(){
		conta = new Conta();
		conta.setEntradaSaida(EntradaSaida.ENTRADA);
	}
		
	public void salvar() {
		
		if(this.conta.getId() == null){
			this.conta.setDataCadastro(new Date());
		}
		
		this.conta.setNumeroParcela(this.conta.getNumero()+'-'+this.conta.getParcela());
		
		this.recalcularConta();
		
		this.conta.setEntradaSaida(EntradaSaida.ENTRADA);
		
		this.conta = cadastroContaService.salvar(this.conta);
		FacesUtil.addInfoMessage("Conta a Pagar salva com sucesso!");
	}
	
	public Conta getConta() {
		return conta;
	}
	
	//faz o calculo dos totais do pedido
	public void recalcularConta(){
		if(this.conta != null){
			this.conta.recalcularValorRestante();
		}
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public boolean isEditando(){
		return this.conta.getId() != null;
	}
	
	public Meses[] getMeses(){
		return Meses.values();
	}
	
	public FormaPagamento[] getFormaPagamentos(){
		return FormaPagamento.values();
	}
	
	public ContaSituacao[] getContaSituacaos(){
		return ContaSituacao.values();
	}
	
	public List<ContaCaixa> completarContaCaixa(String nome){
		return contasCaixas.porNome(nome);
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
}