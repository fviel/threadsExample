/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.viel.threads;

/**
 *
 * @author fernando
 */
public class ThreadsFernando extends Thread{

    public ThreadsFernando(String nome) {
        super();
        this.nome = nome;       
    }    
    
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void run(){
        long startTime = System.currentTimeMillis();
        int i = 0;
        
        while(!Thread.currentThread().isInterrupted()){
            System.out.println(this.getName() + ": Thread " + nome +" rodando aos " + startTime + "ms ... " + i++);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}


