package com.appasystem.pedidovenda.controller.modulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.modulo.EModulos;
import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.service.CadastroModuloService;
import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModuloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Modulo modulo;
	private List<EModulos> eModulosMaster;
	
	@Inject
	private CadastroModuloService cadastroModuloService;
	
	public CadastroModuloBean() {
		limpar();
		eModulosMaster = new ArrayList<EModulos>();
	}
	
	public void inicializar(){
	
		carregarModulosMaster();

	}
	
	public void carregarModulosMaster(){
		
		EModulos[] modulosMaster = EModulos.values();
		
		for (EModulos eMod : modulosMaster) {
			this.eModulosMaster.add(eMod);
		}
		
	}
	
	//limpar o formulário
	private void limpar(){
		modulo = new Modulo();
	}
		
	public void salvar() {
		this.modulo = cadastroModuloService.salvar(this.modulo);
		FacesUtil.addInfoMessage("Modulo salvo com sucesso!");
	}
	
	public Modulo getModulo() {
		return modulo;
	}
	
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	public List<EModulos> geteModulosMaster() {
		return eModulosMaster;
	}

	public boolean isEditando(){
		return this.modulo.getId() != null;
	}
}