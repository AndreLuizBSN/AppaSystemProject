package com.appasystem.pedidovenda.date;

import java.util.Date;

public class MesesClass {

	public Meses getMesAtual(){
			
		ManipulacaoDataHora m = new ManipulacaoDataHora(null, null);
		int mesNum = m.mesNumerico(new Date());
		Meses mesRet = Meses.JANEIRO;
		
		for (Meses mes : Meses.values()) {
			if(mes.getNumMes() == mesNum){
				mesRet = mes;
			}
		}
		
		return mesRet;
		
	}
	
}
