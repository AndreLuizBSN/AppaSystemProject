package com.appasystem.pedidovenda.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.repository.Usuarios;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;

	private DualListModel<String> cities;
    
	@Transactional
	public Usuario salvar(Usuario usuario){
		Usuario usuarioExistente = usuarios.porId(usuario.getId());
		
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuário com o ID informado.");
		}
		
		return usuarios.guardar(usuario);
		
	}
	
	public void inicializar() {
        //Cities
        List<String> citiesSource = new ArrayList<String>();
        List<String> citiesTarget = new ArrayList<String>();
         
        citiesSource.add("San Francisco");
        citiesSource.add("London");
        citiesSource.add("Paris");
        citiesSource.add("Istanbul");
        citiesSource.add("Berlin");
        citiesSource.add("Barcelona");
        citiesSource.add("Rome");
         
        cities = new DualListModel<String>(citiesSource, citiesTarget);
          
    }

	public DualListModel<String> getCities() {
		return cities;
	}

	public void setCities(DualListModel<String> cities) {
		this.cities = cities;
	}
	
    
    
    
}
