package com.appasystem.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.repository.Usuarios;
import com.appasystem.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{

	//@Inject
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Usuario retorno = null;

		if(value != null){
			Long id = new Long(value);
			retorno = usuarios.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null){
			
			Usuario usuario = (Usuario) value;
			
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		
		return "";
	}

	
	
}
