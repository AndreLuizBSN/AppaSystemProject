package com.appasystem.pedidovenda.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ManipulacaoDataHora {

    Date date;
    String format;
    Locale locale;
    DateFormat dateFormat;
    
    public ManipulacaoDataHora(String format, Locale locale){
        this.date = new Date();
        this.format = format;
        this.locale = locale;
    }
    
    public String getDataHoraFormatada(){
        
        if(this.locale != null){
            this.dateFormat = new SimpleDateFormat(this.format, locale);
        }else{
            this.dateFormat = new SimpleDateFormat(this.format);
        }
        return dateFormat.format(date);
    }
    
    public Date dataAdicionar(Date data, int numeroDias){
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(data);
    	c.add(Calendar.DAY_OF_MONTH, numeroDias);
    	return c.getTime();
    	
    }
    
    @SuppressWarnings("static-access")
	public int mesNumerico(Date data){
    	Calendar c = Calendar.getInstance();
    	c.setTime(data);
		return c.MONTH;
    }
    
    @SuppressWarnings("static-access")
	public int anoNumerico(Date data){
    	Calendar c = Calendar.getInstance();
    	c.setTime(data);
		return c.YEAR;
    }
    
}
