package com.appasystem.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.repository.ContasCaixas;
import com.appasystem.pedidovenda.util.jpa.Transactional;
import com.appasystem.pedidovenda.util.number.Money;

public class CadastroContaCaixaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContasCaixas contasCaixas;
	
	@Inject
	private Money money;

	@Transactional
	public ContaCaixa salvar(ContaCaixa contaCaixa){
		
		if(contaCaixa.isNovo()){
			contaCaixa.setDataCadastro(new Date());
			contaCaixa.setDataUltimoFechamento(new Date());
		}else{
			ContaCaixa compara = contasCaixas.porId(contaCaixa.getId());
			
			//Compara os saldos
			if(money.converterMoney(compara.getSaldo()) != money.converterMoney(contaCaixa.getSaldo())){
				contaCaixa.setDataUltimoFechamento(new Date());
			}
		}
		
		
		return contasCaixas.guardar(contaCaixa);
		
	}
	
}
