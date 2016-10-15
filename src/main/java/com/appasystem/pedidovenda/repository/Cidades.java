package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.appasystem.pedidovenda.model.localidade.Cidade;
import com.appasystem.pedidovenda.model.localidade.Estado;
import com.appasystem.pedidovenda.repository.filter.CidadeFilter;

public class Cidades implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	/*Metodos para a tela de cadastro de estado - ini*/
	public Cidade guardar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	public List<Cidade> porNome(String nome) {
		return manager.createQuery("from Cidade "
				+ "where upper(nome) like :nome"
				, Cidade.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
			
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cidade> filtrados(CidadeFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}

	public Estado buscaEstado(Cidade cidade) {
		return cidade.getEstado();
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
		

}
