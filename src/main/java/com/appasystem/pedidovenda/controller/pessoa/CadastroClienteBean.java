package com.appasystem.pedidovenda.controller.pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.cliente.Endereco;
import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.model.pessoa.PessoaEndereco;
import com.appasystem.pedidovenda.model.pessoa.TipoEndereco;
import com.appasystem.pedidovenda.model.pessoa.TipoPessoa;
import com.appasystem.pedidovenda.repository.Cidades;
import com.appasystem.pedidovenda.service.CadastroPessoaService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private PessoaEndereco enderecoSelecionado;
	
	private List<PessoaEndereco> enderecos;
	
	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	@Inject
	private Cidades cidades;
	
	public CadastroClienteBean() {
		limpar();
	}
	
	public void inicializar() {
		
		this.enderecoSelecionado = new PessoaEndereco();
		
		if(this.pessoa.getId() != null){
			this.enderecos = this.pessoa.getEnderecos();
		}else{
			this.enderecos = new ArrayList<PessoaEndereco>();
		}
		
	}
	
	//limpar o formulário
	private void limpar(){
		pessoa = new Pessoa();
	}
		
	public void salvar() {
		
		if(this.pessoa.getId() == null){
			this.pessoa.setCliente(true);
			this.pessoa.setFornecedor(false);
			this.pessoa.setFuncionario(false);
			this.pessoa.setTransportadora(false);
		}
		
		this.pessoa = cadastroPessoaService.salvar(this.pessoa);
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
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

	public PessoaEndereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(PessoaEndereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	//busca as cidades por nome
	public List<Cidade> completarCidade(String nome){
		return cidades.porNome(nome);
	}
	
	public List<PessoaEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<PessoaEndereco> enderecos) {
		this.enderecos = enderecos;
	}

	//carrega o Estado
	public void carregarEstado(){
		
		if(this.enderecoSelecionado.getCidade() != null){
			this.enderecoSelecionado.setUf(cidades.buscaEstado(this.enderecoSelecionado.getCidade()));
		}
	}
	
	//carrega lista de tipos de endereço
	public TipoEndereco[] getTipoEnderecos(){
		return TipoEndereco.values();
	}
	
	//Editar um endereço da Lista
	public void editarEndereco(int linha){
		this.enderecoSelecionado = enderecos.get(linha);
		this.enderecos.remove(linha);
	}

	//Adicionar Endereço
	public void adicionarEndereco(){
		
		this.enderecos.add(enderecoSelecionado);
		limparEnderecoSelecionado();
	}
	
	private void limparEnderecoSelecionado() {
		this.enderecoSelecionado.setCep("");
		this.enderecoSelecionado.setCidade(null);
		this.enderecoSelecionado.setComplemento("");
		this.enderecoSelecionado.setLogradouro("");
		this.enderecoSelecionado.setNumero("");
		this.enderecoSelecionado.setPessoa(getPessoa());
		this.enderecoSelecionado.setUf(null);
	}

	public boolean isEditando(){
		return this.pessoa.getId() != null;
	}

	
}