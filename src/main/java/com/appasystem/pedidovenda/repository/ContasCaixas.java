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

import com.appasystem.pedidovenda.model.financeiro.ContaCaixa;
import com.appasystem.pedidovenda.repository.filter.ContaCaixaFilter;

public class ContasCaixas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public List<ContaCaixa> filtrados(ContaCaixaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContaCaixa.class);
		
		if(!filtro.getNome().isEmpty() || filtro.getNome() != null){
			//acessamos o nome do cliente associado ao pedido pelo alias 'c' criado anteriormente
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public ContaCaixa porId(Long id) {
		return manager.find(ContaCaixa.class, id);
	}
	
	public List<ContaCaixa> porNome(String nome) {
		return manager.createQuery("from ContaCaixa "
				+ "where upper(nome) like :nome"
				, ContaCaixa.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
			
	
	}
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
	public ContaCaixa guardar(ContaCaixa contaCaixa) {
		return manager.merge(contaCaixa);
	}

}
