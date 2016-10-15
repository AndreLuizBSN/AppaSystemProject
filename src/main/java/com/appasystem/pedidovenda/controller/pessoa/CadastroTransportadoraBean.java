package com.appasystem.pedidovenda.controller.pessoa;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.model.pessoa.TipoPessoa;
import com.appasystem.pedidovenda.service.CadastroPessoaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTransportadoraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	
	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	public CadastroTransportadoraBean() {
		limpar();
	}
	
	public void inicializar() {
		
	}
	
	//limpar o formulário
	private void limpar(){
		pessoa = new Pessoa();
	}
		
	public void salvar() {
		
		if(this.pessoa.getId() == null){
			this.pessoa.setCliente(false);
			this.pessoa.setFornecedor(false);
			this.pessoa.setFuncionario(false);
			this.pessoa.setTransportadora(true);
		}
		
		this.pessoa = cadastroPessoaService.salvar(this.pessoa);
		FacesUtil.addInfoMessage("Transportadora salva com sucesso!");
	}
	
	public TipoPessoa[] getTipoPessoas(){
		return TipoPessoa.values();
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isEditando(){
		return this.pessoa.getId() != null;
	}
	
}