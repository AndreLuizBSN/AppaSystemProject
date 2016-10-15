package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import com.appasystem.pedidovenda.model.usuario.Usuario;
import com.appasystem.pedidovenda.repository.filter.UsuarioFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;
import com.appasystem.pedidovenda.util.string.AppaMD5;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    
    public Usuario porId(Long id){
    	try{
    		return manager.find(Usuario.class, id);
    	}catch(IllegalArgumentException e){
    		return null;
    	}
    }
    
    public List<Usuario> raizes(){
        return manager.createQuery("from Usuario",
        		Usuario.class).getResultList();
    }

    public Usuario guardar(Usuario usuario) {
    	
    	/*Colocando a senha com MD5*/
    	
    	usuario = this.validaSenha(usuario);
    	return manager.merge(usuario);
	}
    
    private Usuario validaSenha(Usuario usuario) {
		if(usuario.getId() != null){
	    	Usuario outro = this.porId(usuario.getId());
	    	
	    	if(outro.getSenha() != usuario.getSenha()){
	    		usuario.setSenha(AppaMD5.md5(usuario.getSenha()));
	    	}
    	}else{
    		usuario.setSenha(AppaMD5.md5(usuario.getSenha()));
    	}
    	return usuario;
	}

	@Transactional
	public void remover(Usuario usuario){
		
		try{
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Usuário "+usuario.getId()+" - "+usuario.getNome()+" está sendo usado. Não pode ser excluído!");
		}
	
	}
    
    public Usuario porNome(String nome) {
		try {
			return manager.createQuery("from Usuario where upper(nome) = :nome", Usuario.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
    public Usuario porEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
				.setParameter("email", email.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
    
}
