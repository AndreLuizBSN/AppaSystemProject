package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.appasystem.pedidovenda.model.financeiro.Conta;
import com.appasystem.pedidovenda.model.financeiro.EntradaSaida;
import com.appasystem.pedidovenda.repository.filter.ContaFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class Contas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	
	@Transactional
	public void remover(Conta conta){
		
		try{
			conta = porId(conta.getId());
			manager.remove(conta);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("A conta "+conta.getId()+" - "+conta.getNumero()+" não pode ser excluído!");
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> filtrados(ContaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Conta.class);
		
		if(!filtro.getNumero().isEmpty() || filtro.getNumero() != null){
			criteria.add(Restrictions.ilike("numero", filtro.getNumero(), MatchMode.ANYWHERE));
		}
		
		if(EntradaSaida.ENTRADA.equals(filtro.getEntradaSaida())){
			criteria.add(Restrictions.eq("entradaSaida", EntradaSaida.ENTRADA));
		}else{
			criteria.add(Restrictions.eq("entradaSaida", EntradaSaida.SAIDA));
		}
		
		if(filtro.getSituacaos() != null && filtro.getSituacaos().length > 0){
			//adicionamos uma restrição in passando um array de constantes statusPedido
			criteria.add(Restrictions.in("situacao", filtro.getSituacaos()));
		}
		
		
		return criteria.addOrder(Order.desc("dataVencimento")).list();
		
	}

	public Conta porId(Long id) {
		return manager.find(Conta.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
	public Conta guardar(Conta conta) {
		return manager.merge(conta);
	}

}
