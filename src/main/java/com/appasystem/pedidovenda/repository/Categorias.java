package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import com.appasystem.pedidovenda.model.produto.Categoria;
import com.appasystem.pedidovenda.repository.filter.CategoriaFilter;
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

public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    public Categoria porId(Long id){
    	return manager.find(Categoria.class, id);
    }
    
    public List<Categoria> raizes(){
        return manager.createQuery("from Categoria where categoriaPai is null",
        		Categoria.class).getResultList();
    }

    public List<Categoria> subCategoriasDe(Categoria categoriaPai){
    	return manager.createQuery("from Categoria where categoriaPai = :raiz",
    			Categoria.class).setParameter("raiz", categoriaPai).getResultList();
    }
    
    public Categoria guardar(Categoria categoria) {
		return manager.merge(categoria);
	}
    
    @Transactional
	public void remover(Categoria categoria){
		
		try{
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Categoria "+categoria.getId()+" - "+categoria.getDescricao()+" está sendo usada. Não pode ser excluída!");
		}
	
	}
    
    public Categoria porDescricao(String descricao) {
		try {
			return manager.createQuery("from Categoria where upper(descricao) = :descricao", Categoria.class)
				.setParameter("descricao", descricao.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<Categoria> filtrados(CategoriaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		
		if(!filtro.getDescricao().isEmpty()){
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("descricao")).list();
		
	}
    
}
