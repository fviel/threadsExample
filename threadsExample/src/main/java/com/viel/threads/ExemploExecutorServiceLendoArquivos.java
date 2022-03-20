/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viel.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fernando
 */
public class ExemploExecutorServiceLendoArquivos {

    public static void main(String... args) {

        //cria um thread pool de 10 threads fixas
        ExecutorService exec = Executors.newFixedThreadPool(3);

        Callable<List<String>> atividade1 = () -> {
            TesteArquivos ta = new TesteArquivos("/home/fernando/testeArquivo1.txt", "tfv1");
            List<String> contentReaded = ta.readBufferedFileContent();
            return contentReaded;
        };
        
        Callable<List<String>> atividade2 = () -> {
            TesteArquivos ta = new TesteArquivos("/home/fernando/testeArquivo1.txt", "tfv2");
            List<String> contentReaded = ta.readBufferedFileContent();
            return contentReaded;
        };
        
        Callable<List<String>> atividade3 = () -> {
            TesteArquivos ta = new TesteArquivos("/home/fernando/testeArquivo1.txt", "tfv3");
            List<String> contentReaded = ta.readBufferedFileContent();
            return contentReaded;
        };
        List<Callable<List<String>>> atividades = new ArrayList<>();
        atividades.add(atividade1);
        atividades.add(atividade2);
        atividades.add(atividade3);
        try {
            List<Future<List<String>>> retornosFuturos = exec.invokeAll(atividades);
            
            for(Future<List<String>> f : retornosFuturos){
                printarRetornoFuturo(f);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        exec.shutdown();
        List<Runnable> tasksNaoExecutadas;
        try {
            if (!exec.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                tasksNaoExecutadas = exec.shutdownNow();
            }
        } catch (InterruptedException ie) {
            tasksNaoExecutadas = exec.shutdownNow();
        }
    }

    private static void printarRetornoFuturo(Future<List<String>> retorno) {
        List<String> resposta = null;
        try {
            //chamar o get() enquanto estão executando as threads pode bloquear
            resposta = retorno.get();
            for(String s : resposta){
                System.out.println(s);
            }            
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }
}

//----------------------
class TesteArquivos {

    private String path;
    private String readerName;

    public TesteArquivos(String path, String name) {
        this.path = path;
        this.readerName = name;
    }

    public List<String> readBufferedFileContent() {
        File source = new File(path);
        List<String> dados = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            String s;
            while ((s = reader.readLine()) != null) {
                dados.add(readerName + " - " + s);
            }

            return dados;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    private void print(List<String> dados) {
        for (String s : dados) {
            System.out.println(s);
        }
    }

    public void readAndPrint() {
        List<String> dados = readBufferedFileContent();
        print(dados);
    }

}
