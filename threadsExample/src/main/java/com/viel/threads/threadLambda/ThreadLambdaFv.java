/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viel.threads.threadLambda;

/**
 * Usando lambda, basta aplicar a interface Runnable
 * @author fernando
 */
public class ThreadLambdaFv {

    public static void main(String[] args) {
        Runnable tfv = () -> {
            Long startTime = System.currentTimeMillis();
            int i = 0;

            while (!Thread.currentThread().isInterrupted()) {
                //System.out.println(this.getName() + ": Thread rodando aos " + startTime + "ms ... " + i++);
                System.out.println("Thread rodando aos " + startTime + "ms ... " + i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        tfv.run();
        
        Thread tfv2 = new Thread(()-> System.out.println("foo"));
        tfv2.run();
    }
}



