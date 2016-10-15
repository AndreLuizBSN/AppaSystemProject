package com.appasystem.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.appasystem.pedidovenda.model.produto.Produto;
import com.appasystem.pedidovenda.repository.filter.ProdutoFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	/*Metodos para a tela de cadastro de produtos - ini*/
	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}
	
	@Transactional
	public void remover(Produto produto){
		
		try{
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Produto "+produto.getSku()+" - "+produto.getNome()+" está sendo usado. Não pode ser excluído!");
		}
	
	}
	
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	/*Metodos para a tela de cadastro de produtos - fim*/

	/*Metodos para a tela de Lista de produtos - ini*/
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if(!filtro.getSku().isEmpty()){
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		/*
		 * MatchMode.ANYWHERE
		 * nome like '%joao%'
		 * 
		 * MatchMode.END
		 * nome like '%joao'
		 * 
		 * MatchMode.START
		 * nome like 'joao%'
		 * 
		 * */
		
		if(!filtro.getNome().isEmpty()){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getFiltroRapido() != -1){
			if(filtro.getFiltroRapido() == 0){
				criteria.add(Restrictions.eq("ativo", false));
			}else{
				criteria.add(Restrictions.eq("ativo", true));
			}
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> porNome(String nome) {
		return manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
			.setParameter("nome", nome.toUpperCase()+"%")
			.getResultList();
		
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
}
