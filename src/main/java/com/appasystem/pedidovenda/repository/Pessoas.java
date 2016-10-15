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

import com.appasystem.pedidovenda.model.pessoa.Pessoa;
import com.appasystem.pedidovenda.repository.filter.PessoaFilter;
import com.appasystem.pedidovenda.service.NegocioException;
import com.appasystem.pedidovenda.util.jpa.Transactional;
import com.appasystem.pedidovenda.util.string.AtivoInativoTodos;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	/*Metodos para a tela de cadastro de produtos - ini*/
	public Pessoa guardar(Pessoa pessoa) {
		return manager.merge(pessoa);
	}
	
	@Transactional
	public void remover(Pessoa pessoa){
		
		try{
			pessoa = porId(pessoa.getId());
			manager.remove(pessoa);
			manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Pessoa "+pessoa.getId()+" - "+pessoa.getRazaoSocial()+" está sendo usado. Não pode ser excluído!");
		}
	
	}

	public List<Pessoa> porNome(String razaoSocial) {
		return manager.createQuery("from Pessoa "
				+ "where upper(razaoSocial) like :razaoSocial"
				, Pessoa.class).setParameter("razaoSocial", razaoSocial.toUpperCase() + "%")
				.getResultList();
			
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> filtrados(PessoaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pessoa.class);
		
		if(!filtro.getRazaoSocial().isEmpty()){
			criteria.add(Restrictions.ilike("razaoSocial", filtro.getRazaoSocial(), MatchMode.ANYWHERE));
		}
		
		if(!filtro.getCnpjCpf().isEmpty()){
			criteria.add(Restrictions.ilike("cnpjCpf", filtro.getCnpjCpf(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getAtivo().equals(AtivoInativoTodos.ATIVO)){
			criteria.add(Restrictions.eq("ativo", true));
		}else if(filtro.getAtivo().equals(AtivoInativoTodos.INATIVO)){
			criteria.add(Restrictions.eq("ativo", false));
		}

		if(filtro.getCliente().equals(AtivoInativoTodos.ATIVO)){
			criteria.add(Restrictions.eq("cliente", true));
		}else if(filtro.getAtivo().equals(AtivoInativoTodos.INATIVO)){
			criteria.add(Restrictions.eq("cliente", false));
		}

		if(filtro.getFornecedor().equals(AtivoInativoTodos.ATIVO)){
			criteria.add(Restrictions.eq("fornecedor", true));
		}else if(filtro.getAtivo().equals(AtivoInativoTodos.INATIVO)){
			criteria.add(Restrictions.eq("fornecedor", false));
		}

		if(filtro.getFuncionario().equals(AtivoInativoTodos.ATIVO)){
			criteria.add(Restrictions.eq("funcionario", true));
		}else if(filtro.getAtivo().equals(AtivoInativoTodos.INATIVO)){
			criteria.add(Restrictions.eq("funcionario", false));
		}
		
		if(filtro.getTransportadora().equals(AtivoInativoTodos.ATIVO)){
			criteria.add(Restrictions.eq("transportadora", true));
		}else if(filtro.getAtivo().equals(AtivoInativoTodos.INATIVO)){
			criteria.add(Restrictions.eq("transportadora", false));
		}

		return criteria.addOrder(Order.asc("razaoSocial")).list();
		
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
}
