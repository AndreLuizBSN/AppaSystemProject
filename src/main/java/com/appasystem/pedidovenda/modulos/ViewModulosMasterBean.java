package com.appasystem.pedidovenda.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.modulo.EModulos;
import com.appasystem.pedidovenda.model.modulo.Modulo;

@ViewScoped
@Named
public class ViewModulosMasterBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Modulo> modulosListar = new ArrayList<Modulo>();
	private EModulos[] eModuloses = EModulos.values();

	public ViewModulosMasterBean(){

		carregarModulosMaster();
		
	}
	
	public void carregarModulosMaster(){
		
		for (EModulos eModulos : eModuloses) {
			Modulo modulo = new Modulo();
			modulo.setNome(eModulos.getDescricao());
			modulo.seteModulos(eModulos);
			modulo.setDiretorio("Modulo.xhtml?modulo="+eModulos.toString());
			modulo.setDescricaoModulo(eModulos.getDescricaoModulo());
			modulosListar.add(modulo);
		}
		
	}
	
	public String eModulo(String nomeModulo){

		for (EModulos eModulos : eModuloses) {
			if(eModulos.getDescricao().equals(nomeModulo)){
				return eModulos.toString();
			}
		}

		return "";
	}


	public List<Modulo> getModulosListar() {
		return modulosListar;
	}

}
