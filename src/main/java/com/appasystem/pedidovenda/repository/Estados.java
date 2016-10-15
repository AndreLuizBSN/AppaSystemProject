package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.repository.filter.EstadoFilter;

public class Estados implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	/*Metodos para a tela de cadastro de estado - ini*/
	public Estado guardar(Estado estado) {
		return manager.merge(estado);
	}
	
	public List<Estado> porNome(String nome) {
		return manager.createQuery("from Estado "
				+ "where upper(nome) like :nome"
				, Estado.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
			
	
	}
	
	public List<Estado> raizes(){
        return manager.createQuery("from Estado",
        		Estado.class).getResultList();
    }
	
	public Estado porSigla(String sigla) {
		try {
			return manager.createQuery("from Estado "
					+ "where upper(sigla) = :sigla"
					, Estado.class).setParameter("sigla", sigla.toUpperCase())
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
			
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> filtrados(EstadoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estado.class);
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(!filtro.getSigla().isEmpty()){
			criteria.add(Restrictions.eq("sigla", filtro.getSigla()));
		}
		
		if(filtro.getRegiao() != null){
			//adicionamos uma restrição in passando um array de constantes statusPedido
			criteria.add(Restrictions.eq("regiao", filtro.getRegiao()));
		}
		
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
		

}
