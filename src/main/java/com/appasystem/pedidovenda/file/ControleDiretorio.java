package com.appasystem.pedidovenda.file;

public class ControleDiretorio {

    //pega o diretório padrão do apache dentro da pasta /bin
    private String local = System.getProperty("user.dir");
    
    //retorna o local que deseja baseando-se na pasta raíz do tomcat
    public String getLocalDir(){
        
        this.local = this.local.replaceFirst("/bin", "/logs");
        
        return this.local;
    }
    
    public String controleDiretorioTempArq(){
        
        this.local = this.local.replaceFirst("/bin", "/webapps/tempArq/");
        
        return this.local;
    }
    
    public String getDiretorioRoot(){
    	return "";
    }
    
}
