package com.appasystem.pedidovenda.modulos;

import java.util.ArrayList;
import java.util.List;

import com.appasystem.pedidovenda.model.modulo.Modulo;

public class ConfiguracaoModulos {

	private List<Modulo> modulos = new ArrayList<Modulo>();
	
	
	public List<Modulo> popularModulos(){
		modulos.clear();
		
		
		
		/*
		//produto
		Modulo moduloPro = new Modulo();
		moduloPro.seteModulos(EModulos.PRODUTO);
		moduloPro.setDiretorio("produtos/PesquisaProdutos.xhtml");
		moduloPro.setNome("Produtos");
		moduloPro.setDescricaoModulo("Manutenção de registros de produtos");
		moduloPro.setImagem("produtos.png");
		modulos.add(moduloPro);

		//Categoria
		Modulo moduloCat = new Modulo();
		moduloCat.seteModulos(EModulos.PRODUTO);
		moduloCat.setDiretorio("categorias/PesquisaCategorias.xhtml");
		moduloCat.setNome("Categorias");
		moduloCat.setDescricaoModulo("Manutenção de categorias de produtos");
		moduloCat.setImagem("categorias.png");
		modulos.add(moduloCat);

		//cliente
		Modulo moduloCli = new Modulo();
		moduloCli.seteModulos(EModulos.CLIENTE);
		moduloCli.setDiretorio("clientes/PesquisaClientes.xhtml");
		moduloCli.setNome("Clientes");
		moduloCli.setDescricaoModulo("Manutenção de registro de clientes");
		moduloCli.setImagem("clientes.png");
		modulos.add(moduloCli);

		//Pedido
		Modulo moduloPed = new Modulo();
		moduloPed.seteModulos(EModulos.PEDIDO);
		moduloPed.setDiretorio("pedidos/PesquisaPedidos.xhtml");
		moduloPed.setNome("Pedidos");
		moduloPed.setDescricaoModulo("Manutenção de cadastro de pedidos");
		moduloPed.setImagem("pedidos.png");
		modulos.add(moduloPed);

		//Administracao
		Modulo moduloUsu = new Modulo();
		moduloUsu.seteModulos(EModulos.ADMINISTRACAO);
		moduloUsu.setDiretorio("usuarios/PesquisaUsuarios.xhtml");
		moduloUsu.setNome("Usuários");
		moduloUsu.setDescricaoModulo("Manutenção de cadastro de usuários");
		moduloUsu.setImagem("usuarios.png");
		modulos.add(moduloUsu);

		//Administracao
		Modulo moduloGru = new Modulo();
		moduloGru.seteModulos(EModulos.ADMINISTRACAO);
		moduloGru.setDiretorio("usuarios/PesquisaGrupos.xhtml");
		moduloGru.setNome("Grupos");
		moduloGru.setDescricaoModulo("Manutenção de cadastro de grupo de usuários");
		moduloGru.setImagem("grupos.png");
		modulos.add(moduloGru);
		
		//Localidade
		Modulo moduloEst = new Modulo();
		moduloEst.seteModulos(EModulos.LOCALIDADE);
		moduloEst.setDiretorio("localidades/PesquisaEstados.xhtml");
		moduloEst.setNome("Estados");
		moduloEst.setDescricaoModulo("Manutenção de cadastro de Estados");
		moduloEst.setImagem("estados.png");
		modulos.add(moduloEst);
		
		//Localidade
		Modulo moduloCid = new Modulo();
		moduloCid.seteModulos(EModulos.LOCALIDADE);
		moduloCid.setDiretorio("localidades/PesquisaCidades.xhtml");
		moduloCid.setNome("Cidades");
		moduloCid.setDescricaoModulo("Manutenção de cadastro de cidades");
		moduloCid.setImagem("cidades.png");
		modulos.add(moduloCid);
		*/
		return modulos;
	}
	
}
