package com.appasystem.pedidovenda.controller.localidades;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.model.localidade.Regiao;
import com.appasystem.pedidovenda.service.CadastroEstadoService;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;
import com.appasystem.pedidovenda.util.string.AppaString;

@Named
@ViewScoped
public class CadastroEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Estado estado;
	
	@Inject
	private CadastroEstadoService cadastroEstadoService;
	
	public CadastroEstadoBean() {
		limpar();
	}
	
	public void inicializar() {
	}
	
	//limpar o formulário
	private void limpar(){
		estado = new Estado();
	}
		
	public void salvar() {
		
		if(this.estado.getSigla().length() == 2){
			
			AppaString appaString = new AppaString();
			this.estado.setImagem(appaString.desacentuar(this.estado.getNome()));
			
			this.estado.setSigla(this.estado.getSigla().toUpperCase());
			this.estado.setImagem(this.estado.getImagem().toLowerCase()
					.replace(" ", "_")
					+".gif");
			
			this.estado = cadastroEstadoService.salvar(this.estado);
			FacesUtil.addInfoMessage("Estado salvo com sucesso!");
		}else{
			throw new NegocioException("A sigla deve ter 2 caracteres!");
		}
	}
	
	public Regiao[] getRegioes(){
		return Regiao.values();
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public boolean isEditando(){
		return this.estado.getId() != null;
	}
	
}