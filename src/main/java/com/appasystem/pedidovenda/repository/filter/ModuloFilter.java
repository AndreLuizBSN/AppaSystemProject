package com.appasystem.pedidovenda.repository.filter;

import java.io.Serializable;

import com.appasystem.pedidovenda.model.modulo.EModulos;

public class ModuloFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private EModulos eModulos;

	public EModulos geteModulos() {
		return eModulos;
	}

	public void seteModulos(EModulos eModulos) {
		this.eModulos = eModulos;
	}
	
	
}
