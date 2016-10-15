package com.appasystem.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TesteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer progress;
	
	public Integer getProgress() {
        if(this.progress == null) {
        	this.progress = 0;
        	System.out.println("entrou");
        }
        else {
        	this.progress = this.progress + (int)(Math.random() * 35);
             
            if(this.progress > 100)
            	this.progress = 100;
        }
        
        System.out.println(this.progress);
        
        return this.progress;
     
    }
 
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    
    public void onComplete() {
    	
    	System.out.println("Carregamento Completo");
    	
    }
     
    public void cancel() {
        progress = null;
    }
	
	
}
