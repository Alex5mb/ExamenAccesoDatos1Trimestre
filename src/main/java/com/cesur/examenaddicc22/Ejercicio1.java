package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        
        /* añadir código */
        
         try(var fr = new BufferedReader(new FileReader(archivo));)
         {
             String s;
             
             int linea = 0;
             int carac = 0;
             int palabra = 0;
                               
         while((s=fr.readLine())!=null){
              linea++;
                 
              String[] palabras = s.split("");
              for(String p: palabras){
                carac++;
             }
              
            
             String[] palabras2 = s.split("[\\s]");
              for(String p: palabras2){
                  palabra++;
              }
              
          
         }
             System.out.println("El numero de lineas del pom es: "  + linea);
             System.out.println("El numero de caracteres del pom es: "  + carac);
             System.out.println("El numero de palabras del pom es: " + palabra);
     }   catch (FileNotFoundException ex) {
             Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        System.out.println("resuelto");
    }
    
}
