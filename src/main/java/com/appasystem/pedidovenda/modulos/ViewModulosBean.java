package com.appasystem.pedidovenda.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.appasystem.pedidovenda.model.modulo.EModulos;
import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.repository.Modulos;
import com.appasystem.pedidovenda.repository.filter.ModuloFilter;

@ViewScoped
@Named
public class ViewModulosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nomeModulo;
	private List<Modulo> modulos;
	private List<Modulo> modulosListar = new ArrayList<Modulo>();
	private String moduloMaster = "";
	private EModulos[] eModuloses = EModulos.values();
	
	@Inject
	private Modulos modulosBanco;
	
	private ModuloFilter filtro;
	
	public ViewModulosBean(){
		filtro = new ModuloFilter();
	}
	
	
	public void inicializar(){
		
		boolean isExiste = isExisteModulo(nomeModulo);
		if(isExiste){
			//modulos = configuracaoModulos.popularModulos();
			filtro.seteModulos(EModulos.valueOf(nomeModulo));
			
			modulos = modulosBanco.filtrados(filtro);
			
			for (Modulo modulo : modulos) {
				if(modulo.geteModulos().toString().equals(nomeModulo)){
					modulosListar.add(modulo);
					
					if(moduloMaster == "" || moduloMaster.equals(modulo.geteModulos().getDescricao())){
						moduloMaster = modulo.geteModulos().getDescricao();
					}
					
				}
			}
		}else{
			
			moduloMaster = "Geral";
			
			carregarModulosMaster();
		
		}
		
	}
	
	private boolean isExisteModulo(String nmModulo) {
		
		for (EModulos eModulos : eModuloses) {
			if(eModulos.toString().equals(nmModulo)){
				return true;
			}
		}
		
		return false;
	}
	
	public void carregarModulosMaster(){
		
		for (EModulos eModulos : eModuloses) {
			Modulo modulo = new Modulo();
			modulo.setNome(eModulos.getDescricao());
			modulo.seteModulos(eModulos);
			modulo.setDiretorio("Modulo.xhtml?modulo="+eModulos.toString());
			modulo.setImagem(eModulos.getImagem());
			modulo.setDescricaoModulo(eModulos.getDescricaoModulo());
			modulosListar.add(modulo);
		}
		
	}
	
	public String getNomeModulo() {
		return nomeModulo;
	}

	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	public List<Modulo> getModulosListar() {
		return modulosListar;
	}

	public String getModuloMaster() {
		return moduloMaster;
	}
	
}
