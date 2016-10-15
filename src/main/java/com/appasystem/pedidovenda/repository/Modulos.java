package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import com.appasystem.pedidovenda.model.modulo.Modulo;
import com.appasystem.pedidovenda.repository.filter.ModuloFilter;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Modulos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    public List<Modulo> raizes(){
        return manager.createQuery("from Modulo",
        		Modulo.class).getResultList();
    }
    
    public Modulo guardar(Modulo modulo) {
		return manager.merge(modulo);
	}
    
    @SuppressWarnings("unchecked")
	public List<Modulo> filtrados(ModuloFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Modulo.class);
		
		if(filtro.geteModulos() != null){
			criteria.add(Restrictions.eq("eModulos", filtro.geteModulos()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public Modulo porId(Long id) {
		return manager.find(Modulo.class, id);
	}
    
}
