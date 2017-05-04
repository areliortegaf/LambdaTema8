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
public interface NuevoSaludo extends saludo {
    @Override
    default void saludar(){
        System.out.println("Hola BB <3");
    }
}
