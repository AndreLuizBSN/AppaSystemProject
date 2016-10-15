package com.appasystem.pedidovenda.controller.modulo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.modulo.EModulos;
import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.repository.Modulos;
import com.appasystem.pedidovenda.repository.filter.ModuloFilter;

@Named
@ViewScoped
public class PesquisaModulosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ModuloFilter filtro;
	private List<Modulo> modulosFiltradas;
	
	@Inject
	private Modulos modulos;
		
	public void pesquisar(){
		modulosFiltradas = modulos.raizes();
	}
		
	public EModulos[] getEModulos(){
		return EModulos.values();
	}
	
	public List<Modulo> getModulosFiltradas() {
		return modulosFiltradas;
	}

	public ModuloFilter getFiltro() {
		return filtro;
	}
	
}