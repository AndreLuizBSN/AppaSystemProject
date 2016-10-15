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

import com.appasystem.pedidovenda.model.pagamento.CondicaoPagamento;
import com.appasystem.pedidovenda.repository.filter.CondicaoPagamentoFilter;

public class CondicoesPagamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public List<CondicaoPagamento> filtrados(CondicaoPagamentoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CondicaoPagamento.class);
		
		if(!filtro.getNome().isEmpty()){
			//acessamos o nome do cliente associado ao pedido pelo alias 'c' criado anteriormente
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getIntAtivo() != -1){
			if(filtro.getIntAtivo() == 0){
				criteria.add(Restrictions.eq("ativo", false));
			}else{
				criteria.add(Restrictions.eq("ativo", true));
			}
		}

		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public CondicaoPagamento porId(Long id) {
		return manager.find(CondicaoPagamento.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
	public CondicaoPagamento guardar(CondicaoPagamento condicaoPagamento) {
		return manager.merge(condicaoPagamento);
	}

	public List<CondicaoPagamento> porNome(String nome) {
		return manager.createQuery("from CondicaoPagamento "
				+ "where upper(nome) like :nome"
				, CondicaoPagamento.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

}
