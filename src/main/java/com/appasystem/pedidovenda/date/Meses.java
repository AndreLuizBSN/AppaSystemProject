package com.appasystem.pedidovenda.date;

public enum Meses {

	JANEIRO("Janeiro", 1)
	, FEVEREIRO("Fevereiro",2)
	, MARCO("Março",3)
	, ABRIL("Abril",4)
	, MAIO("Maio",5)
	, JUNHO("Junho",6)
	, JULHO("Julho",7)
	, AGOSTO("Agosto",8)
	, SETEMBRO("Setembro",9)
	, OUTUBRO("Outubro",10)
	, NOVEMBRO("Novembro",11)
	, DEZEMBRO("Dezembro",12);
	
	private String descricao;
	private int numMes;

	private Meses(String descricao, int numMes){
		this.descricao = descricao;
		this.numMes = numMes;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public int getNumMes() {
		return numMes;
	}
	
}
