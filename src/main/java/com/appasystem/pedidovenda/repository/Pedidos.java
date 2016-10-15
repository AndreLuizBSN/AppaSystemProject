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

import com.appasystem.pedidovenda.model.pedido.Pedido;
import com.appasystem.pedidovenda.repository.filter.PedidoFilter;

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private EntityManager manager;

	
	@SuppressWarnings("unchecked")
	public List<Pedido> filtrados(PedidoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pedido.class)
				//fazemos uma associação(join) com cliente e nomeamos com 'c'
				.createAlias("cliente", "c")
				//fazemos uma associação(join) com vendedos e nomeamos com 'v'
				.createAlias("vendedor", "v");
		
		
		if(filtro.getNumeroDe() != null){
			//id deve ser maior ou igual(ge = greater ou equal) ao filtro numeroDe
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}
		
		if(filtro.getNumeroAte() != null){
			//id deve ser Meno ou igual(le = lower ou equal) ao filtro numeroAte
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}
		
		if(filtro.getDataCriacaoDe() != null){
			//dataCriacao deve ser maior ou igual(ge = greater ou equal) ao filtro DataCriacaoDe
			criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
		}
		
		if(filtro.getDataCriacaoAte() != null){
			//dataCriacao deve ser Menor ou igual(le = lower ou equal) ao filtro DataCriacaoAte
			criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
		}
		
		if(!filtro.getNomeCliente().isEmpty()){
			//acessamos o nome do cliente associado ao pedido pelo alias 'c' criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
		}
		
		if(!filtro.getNomeVendedor().isEmpty()){
			//acessamos o nome do cliente associado ao pedido pelo alias 'c' criado anteriormente
			criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
		}
		
		if(filtro.getStatuses() != null && filtro.getStatuses().length > 0){
			//adicionamos uma restrição in passando um array de constantes statusPedido
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}
		
		
		return criteria.addOrder(Order.asc("id")).list();
		
	}

	public Pedido porId(Long id) {
		return manager.find(Pedido.class, id);
	}
	
	
	/*Metodos para a tela de Lista de produtos - fim*/
	
	public Pedido guardar(Pedido pedido) {
		return manager.merge(pedido);
	}

}
