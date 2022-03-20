/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.viel.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este é o exemplo de usso incorreto de threads, pois eu lanço elas manualmente
 *  e isto gera problemas, pois não faz o gerenciamento de memória
 * @author fernando
 */
public class ExemploThreadUnica {
    
    public static void main(String ... args){
        ThreadsFernando tf1 = new ThreadsFernando("Fernando");
        ThreadsFernando tf2 = new ThreadsFernando("Simone");
//        ThreadsFernando tf3 = new ThreadsFernando("Helena");
//        ThreadsFernando tf4 = new ThreadsFernando("Amenair");
//        ThreadsFernando tf5 = new ThreadsFernando("Osmar");
        tf1.start();
        tf2.start();
//        tf3.start();
//        tf4.start();
//        tf5.start();
        
        tf1.interrupt();
        try {
            tf1.join();
            System.out.println("Fiz o join da thread " + tf1.getName() + tf1.getNome() + " .");
        } catch (InterruptedException ex) {
            System.out.println("thread 1 interrompida");
        }
    }
    
}
