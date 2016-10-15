package com.appasystem.pedidovenda.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoTexto {

    File arquivo;
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    String nomeArquivo = "";
    String dir;
    String[] dados;
    
    public ArquivoTexto(String dir, String[] dados){
        ArquivoTextoLogDados dadosA = new ArquivoTextoLogDados();
        this.nomeArquivo = dadosA.setNome();
        this.dir = dir;
        this.dados = dados;
    }
    
    public void escreverDados(){
        
        try{
        
            arquivo = new File(this.dir+"/"+this.nomeArquivo);
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
            //cria vetor para armazenar texto
            ArrayList<String> texto = new ArrayList<String>();
            
            //adiciona valor ao vetor com o que tem dentro do arquivo
            while(bufferedReader.ready()){
                texto.add(bufferedReader.readLine());
            }
            
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            
            //adiciona o erro que veio por parametro para o vetor
            for(int i=0;i<texto.size();i++){
                bufferedWriter.write(texto.get(i));
                bufferedWriter.newLine();
            }
            for(String s: this.dados){
                /*Imprime o token.*/
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }/*Fim for.*/
            
            bufferedReader.close();
            bufferedWriter.close();
            
        }catch(FileNotFoundException e){
            
            try{
                arquivo.createNewFile();
                escreverDados();
            }catch(IOException ex){
                System.out.println("Erro ao criar arquivo");
            }
        }catch(IOException er){
            System.out.println("Erro ao ler arqquivo");
        }
        
    }
    
    public ArrayList<String> lerDados(String dir, String[] dados){
        
        try{
        
            arquivo = new File(dir+"/"+this.nomeArquivo);
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
            //cria vetor para armazenar texto
            ArrayList<String> texto = new ArrayList<String>();
            
            //adiciona valor ao vetor com o que tem dentro do arquivo
            while(bufferedReader.ready()){
                texto.add(bufferedReader.readLine());
            }
            
            bufferedReader.close();
            
            return texto;
            
        }catch(FileNotFoundException e){
            
            try{
                arquivo.createNewFile();
                escreverDados();
            }catch(IOException ex){
                System.out.println("Erro ao criar arquivo");
            }
        }catch(IOException er){
            System.out.println("Erro ao ler arqquivo");
        }
        
        return null;
        
    }
    
}
