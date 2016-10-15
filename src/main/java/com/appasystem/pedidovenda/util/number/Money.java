package com.appasystem.pedidovenda.util.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Money {

	//Converter BigDecimal em double com 2 casas decimais
	public double converterMoney(BigDecimal valor){
		
		//formado de conversão 2 unidades decimais
		DecimalFormat df = new DecimalFormat("0.##");
        String vx = df.format(valor);
        return Double.parseDouble(vx.replace(",", "."));
		
	}

	public double converterMoedaDouble(double valor){
		return converterMoney(new BigDecimal(valor));
	}
	
	//Traz a diferenca de uma divisao entre BigDecima
	public double diferencaDivisao(BigDecimal arg1, BigDecimal arg2){
		
		double a = converterMoney(arg1);
		double b = converterMoney(arg2);
		double c = a / b;
		c = converterMoney(new BigDecimal(c));
		
		double d = (c * b) - a;
		
		d = converterMoney(new BigDecimal(d));
		
		return d;
		
	}
	
}
