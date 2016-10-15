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

import com.appasystem.pedidovenda.model.cliente.Cliente;
import com.appasystem.pedidovenda.repository.filter.ClienteFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	/*Metodos para a tela de cadastro de produtos - ini*/
	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	@Transactional
	public void remover(Cliente cliente){
		
		try{
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Cliente "+cliente.getId()+" - "+cliente.getNome()+" está sendo usado. Não pode ser excluído!");
		}
	
	}

	public List<Cliente> porNome(String nome) {
		return manager.createQuery("from Cliente "
				+ "where upper(nome) like :nome"
				, Cliente.class).setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
			
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(!filtro.getCnpjCpf().isEmpty()){
			criteria.add(Restrictions.ilike("documentoReceitaFederal", filtro.getCnpjCpf(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
}
