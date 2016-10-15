package com.appasystem.pedidovenda.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ArquivoTextoLog {
    
    public String dir;
    
    public Path path = null;
    Charset utf8 = StandardCharsets.UTF_8;

	private BufferedReader r;
    
    public ArquivoTextoLog(String dirP, Path path){
        this.dir = dirP;
        this.path = path;
    }
   
    public void escreverLog(String excMsg, Exception exception){
        
        try{
            
            System.out.println(path.toString());
            
            escreveText("Log de Erro do sistema");
            
            if(exception.getMessage() != null){
                concatEscreveText("Mensagem de Exceção: "+excMsg);
                concatEscreveText("-------------------");
                concatEscreveText(Arrays.toString(exception.getStackTrace()).replace(',', '\n'));
            }else{
                concatEscreveText("-------------"); 
            }

        }catch(Exception e){
            System.out.println("Erro ao gerar arquivo de log!!!");
            System.out.println(e.getMessage());
            System.out.println("*******************************");
            
        }
        
    }

    
    public void escreveText(String texto){
        /*Escrita de arquivos*/
        try{
            //Usando a linha acima(Ele abre, escreve e fecha automaticamente)
            BufferedWriter w2 = Files.newBufferedWriter(path, utf8);
            w2.write(texto);
            
            System.out.println("Entrou escreveText: "+texto);
            w2.close();
        }catch(Exception e){
            System.out.println("Erro de manipulação de arquivo");
            System.out.println(e.getMessage());
                
        }
    }

    public void concatEscreveText(String texto){
        /*Escrita de arquivos*/
        try{
            //Usando a linha acima(Ele abre, escreve e fecha automaticamente)
            BufferedWriter w2 = Files.newBufferedWriter(path, utf8);
            
            String txtAnt = lerTextoSemEscrever();
            w2.write(txtAnt+"\n"+texto);
            System.out.println("Entrou concatEscreveText: Txt anterior: "+txtAnt);
            
            w2.close();
        }catch(Exception e){
            System.out.println("Erro de manipulação de arquivo");
            System.out.println(e.getMessage());
        }
    }

    public String lerTextoSemEscrever(){
        /*Leitura de Arquivos*/
        try{
            InputStream is = new FileInputStream(path.toString());
            InputStreamReader isr = new InputStreamReader(is);
            r = new BufferedReader(isr);
            System.out.println("Irá retornar da leitura "+r.readLine());
            
            return r.readLine();
            
        }catch (Exception e) {
                System.out.println("Erro ao ler aquivo");
                System.out.println(e.getMessage());
                return "";
        }
    }

    public void lerTexto(){
        /*Leitura de Arquivos*/
        try{
            BufferedReader r = Files.newBufferedReader(path, utf8);
            String linha = null;
            while((linha = r.readLine()) != null){
                    System.out.println(linha);
            }

        }catch (Exception e) {
            System.out.println("Erro ao ler aquivo "+path);
            System.out.println(e.getMessage());
        }
    }

    
}
