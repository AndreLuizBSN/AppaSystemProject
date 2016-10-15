package com.appasystem.pedidovenda.controller.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.repository.Pessoas;
import com.appasystem.pedidovenda.repository.filter.PessoaFilter;
import com.appasystem.pedidovenda.service.CadastroPessoaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;
import com.appasystem.pedidovenda.util.string.AtivoInativoTodos;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PessoaFilter filtro;
	private List<Pessoa> pessoasFiltrados;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Pessoa pessoaSelecionado;
	
	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	public PesquisaClientesBean(){
		this.filtro = new PessoaFilter();
	}
	
	public void excluir(Long id){
		
		
		pessoaSelecionado = pessoas.porId(id);
		
		pessoas.remover(pessoaSelecionado);
		pessoasFiltrados.remove(pessoaSelecionado);
		
		FacesUtil.addInfoMessage("Cliente "+pessoaSelecionado.getId()+" - "+pessoaSelecionado.getRazaoSocial()+" excluído com sucesso!");
		
	}
	
	public void ativarDesativar(Long id, boolean ativo){
		
		//Inverter
		if(ativo){
			ativo = false;
		}else{
			ativo = true;
		}
		
		pessoaSelecionado = pessoas.porId(id);
		pessoaSelecionado.setAtivo(ativo);
		pessoaSelecionado = cadastroPessoaService.salvar(pessoaSelecionado);
		String texto;
		if(ativo){
			texto = "ativado";
		}else{
			texto = "inativado";
		}
		FacesUtil.addInfoMessage("Cliente "+pessoaSelecionado.getId()+" - "+pessoaSelecionado.getRazaoSocial()+" "+texto+" com sucesso!");
		
	}
	
	public void pesquisar(){
		
		this.filtro.setCliente(AtivoInativoTodos.ATIVO);
		this.filtro.setFornecedor(AtivoInativoTodos.TODOS);
		this.filtro.setFuncionario(AtivoInativoTodos.TODOS);
		this.filtro.setTransportadora(AtivoInativoTodos.TODOS);
		
		pessoasFiltrados = pessoas.filtrados(this.filtro);

	}
	
	public AtivoInativoTodos[] getAtivoInativoTodos(){
		return AtivoInativoTodos.values(); 
	}
	
	public List<Pessoa> getPessoasFiltrados() {
		return pessoasFiltrados;
	}
	
	public PessoaFilter getFiltro() {
		return filtro;
	}

	public Pessoa getPessoaSelecionado() {
		return pessoaSelecionado;
	}

	public void setPessoaSelecionado(Pessoa pessoaSelecionado) {
		this.pessoaSelecionado = pessoaSelecionado;
	}
	
}