/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viel.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class ExemploExecutorService {

    public static void main(String... args) {

        //cria um thread pool de 10 threads fixas
        ExecutorService exec = Executors.newFixedThreadPool(10);

        ExecutorService exec2 = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        //ExecutorServices pode executar dois tipos e atividades:
        //Runnables
        //Callables
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        };

        Callable<String> atividade = () -> {
            TimeUnit.MILLISECONDS.sleep(1000);
            return "Tarefa executada";
        };

        //cria lista de atividades
        List<Callable<String>> atividades = new ArrayList<>();
        atividades.add(atividade);
        atividades.add(atividade);
        atividades.add(atividade);
        atividades.add(atividade);
        atividades.add(atividade);

        //aciona o executor, observe que execute é void, não vai lhe dar o retorno das threads
        exec.execute(runnableTask);

        //submit serve para Callable e Runnable, e retorna um tipo Future
        Future<String> retornoFuturo = exec.submit(atividade);
        
        printarRetornoFuturo(retornoFuturo);

        try {
            //invokeAny() - define uma coleção de atividades para o ExecutorService, fazendo com que todas executem e retornem seus resultados de uma atividade (se executada corretamente)
            String resultado = exec.invokeAny(atividades);

            //invokeAll() - define uma coleção de atividades para o ExecutorService, fazendo com que todas executem e retorna os resultados de todas as execuções na forma de lista d eobjetos Future
            List<Future<String>> retornosFuturos = exec.invokeAll(atividades);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        //e pra parar o ExecutorService?
        //shutdow()
        //shutdownNow()
        //awaitTermination()
        //shutdown - não encerra imediatamente, mas o ES irá parar de aceitar 
        //tarefas e encerrará logo após a execução de todas as tarefas do pool
        //exec.shutdown();
        //shutdowNow() tenta destruir o ES imediatamente, mas não garante
        //que todas as threads sejam paradas no mesmo tempo
        //List<Runnable> tasksNaoExecutadas = exec.shutdownNow();
        //A recomendação da Oracle é usar ambos, em conjunto com o AwaitTermiantion
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
    
    private static void printarRetornoFuturo(Future<String> retorno){
        String resposta = null;
        try{
            //chamar o get() enquanto estão executando as threads pode bloquear
            resposta = retorno.get();
            System.out.println("Resposta: " + resposta);
        }catch(Exception ie){
            ie.printStackTrace();
        }
    }
    
    
    

}
