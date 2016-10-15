package com.appasystem.pedidovenda.controller.localidades;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.repository.Estados;
import com.appasystem.pedidovenda.service.CadastroCidadeService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Cidade cidade;
	private List<Estado> estadosRaizes;
	
	@Inject
	private Estados estados;
	
	@Inject
	private CadastroCidadeService cadastroCidadeService;
	
	public CadastroCidadeBean() {
		limpar();
	}
	
	public void inicializar() {
		estadosRaizes = estados.raizes();
	}
	
	//limpar o formulário
	private void limpar(){
		cidade = new Cidade();
	}
		
	public void salvar() {
	
		this.cidade = cadastroCidadeService.salvar(this.cidade);
		FacesUtil.addInfoMessage("Cidade salva com sucesso!");
	
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Estado> getEstadosRaizes() {
		return estadosRaizes;
	}

	public boolean isEditando(){
		return this.cidade.getId() != null;
	}
	
}