/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioscurso6;

/**
 *
 * @author asortega
 */
public interface saludo {
    default void saludar(){
        System.out.println("Hola <3");
    }
}
