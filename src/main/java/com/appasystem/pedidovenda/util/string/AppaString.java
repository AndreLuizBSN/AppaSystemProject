package com.appasystem.pedidovenda.util.string;

public class AppaString {

	public String desacentuar(String valor){
		
		valor = valor.replace("á", "a");
		valor = valor.replace("ã", "a");
		valor = valor.replace("â", "a");
		valor = valor.replace("à", "a");
		valor = valor.replace("é", "e");
		valor = valor.replace("ê", "e");
		valor = valor.replace("í", "i");
		valor = valor.replace("ó", "o");
		valor = valor.replace("õ", "o");
		valor = valor.replace("ô", "o");
		valor = valor.replace("ú", "u");
		valor = valor.replace("ü", "u");
		valor = valor.replace("ç", "c");
		
		return valor;
	}
	
}
