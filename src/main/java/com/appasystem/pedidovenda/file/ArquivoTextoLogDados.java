package com.appasystem.pedidovenda.file;

import com.appasystem.pedidovenda.date.ManipulacaoDataHora;

public class ArquivoTextoLogDados {

    public String setNome(){
        
        ManipulacaoDataHora dataMan = new ManipulacaoDataHora("yyyy-MM-dd", null);
        String dateStr = dataMan.getDataHoraFormatada();
        return dateStr+"-PedidoVenda.log";
        
    }
    
}
