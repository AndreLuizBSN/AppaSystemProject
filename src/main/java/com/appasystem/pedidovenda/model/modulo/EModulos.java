package com.appasystem.pedidovenda.model.modulo;

public enum EModulos {
	
	ADMINISTRACAO("Administração", "Modulos de administração do sistema", "moduloAdministracao.png")
	, FINANCEIRO("Financeiro","Modulo financeiro","modulofinanceiro.png")
	, LOCALIDADE("Localidade", "Modulo de localidades", "moduloLocalidades.png")
	, PEDIDO("Pedido", "Modulos de manutenção de pedidos e seus relacionados","moduloPedidos.png")
	, PESSOA("Pessoa", "Modulo de pessoas e seus relacionamentos", "moduloClientes.png")
	, PRODUTO("Produto", "Modulos de manutenção de produtos e seus relacionados", "moduloProdutos.png");
	
	
	private String descricao;
	private String descricaoModulo;
	private String imagem;
	
	private EModulos(String descricao, String descricaoModulo, String imagem) {
		this.descricao = descricao;
		this.descricaoModulo = descricaoModulo;
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDescricaoModulo() {
		return descricaoModulo;
	}

	public String getImagem() {
		return imagem;
	}
	
}
