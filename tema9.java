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
public class tema9 implements NuevoSaludo {
   
    public tema9(){
     
      saludar();
    }
    
    @Override
    public void saludar(){
        System.out.println(NuevoSaludo.class.getSimpleName());
    }
    
    
    public static void main (String ... args){
        new tema9();
    }
    
}
