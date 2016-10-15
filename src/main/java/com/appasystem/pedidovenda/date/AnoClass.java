package com.appasystem.pedidovenda.date;

import java.util.Date;

public class AnoClass {

	public int getAnoAtual(){
		
		ManipulacaoDataHora m = new ManipulacaoDataHora(null, null);
		return m.anoNumerico(new Date());
		
	}
	
}
