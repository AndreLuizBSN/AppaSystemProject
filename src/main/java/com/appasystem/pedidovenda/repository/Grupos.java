package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import com.appasystem.pedidovenda.model.usuario.Grupo;
import com.appasystem.pedidovenda.repository.filter.GrupoFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    
    public Grupo porId(Long id){
    	return manager.find(Grupo.class, id);
    }
    
    public List<Grupo> raizes(){
        return manager.createQuery("from Grupo",
        		Grupo.class).getResultList();
    }

    public Grupo guardar(Grupo grupo) {
		return manager.merge(grupo);
	}
    
    @Transactional
	public void remover(Grupo grupo){
		
		try{
			grupo = porId(grupo.getId());
			manager.remove(grupo);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Grupo "+grupo.getId()+" - "+grupo.getNome()+" está sendo usado. Não pode ser excluído!");
		}
	
	}
    
    public Grupo porDescricao(String descricao) {
		try {
			return manager.createQuery("from Grupo where upper(descricao) = :descricao", Grupo.class)
				.setParameter("descricao", descricao.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
    public Grupo porNome(String nome) {
		try {
			return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    @SuppressWarnings("unchecked")
	public List<Grupo> filtrados(GrupoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);
		
		if(!filtro.getDescricao().isEmpty()){
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
    
}
