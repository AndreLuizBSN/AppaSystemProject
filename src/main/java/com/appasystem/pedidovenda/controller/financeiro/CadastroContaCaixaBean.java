package com.appasystem.pedidovenda.controller.financeiro;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.service.CadastroContaCaixaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroContaCaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ContaCaixa contaCaixa;
	
	@Inject
	private CadastroContaCaixaService cadastroContaCaixaService;
	
	public CadastroContaCaixaBean() {
		limpar();
	}
	
	public void inicializar() {
		
	}
	
	//limpar o formulário
	private void limpar(){
		contaCaixa = new ContaCaixa();
	}
		
	public void salvar() {
		this.contaCaixa = cadastroContaCaixaService.salvar(this.contaCaixa);
		FacesUtil.addInfoMessage("Conta Caixa salva com sucesso!");
	}
	
	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public boolean isEditando(){
		return this.contaCaixa.getId() != null;
	}
	
}