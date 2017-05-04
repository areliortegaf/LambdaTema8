/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioscurso6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author asortega
 */
public class tema8 {

    //SE USA EN EL COMPIREIFABSENT()
    private static final String placeHolder = "TOD";

    public tema8() {
        usarComputeIfAbsent();
        usarComputeIfPresent();
        usarMerge();
        usarRemoveIf();
        usarMapKeyValuesConStream();
        usarMetodoStreamOf();
        usarLineEnBufferReader();
        usarNIOclasesEnFileStream();
        usarReadAllLines();
        usarFileList();
        usarFilesWalk();
        usarFilesFind();
        usarFlatMap();
    }

    public void usarComputeIfAbsent() {

        Map<String, String> stMap = new HashMap<>();
        stMap.put("WY", "Cheyene");
        stMap.put("SD", "Pierre");
        stMap.put("CU", "Denver");

        stMap.computeIfAbsent("AL", t -> placeHolder);
        System.out.println("Imprimir IfAbsent");
        stMap.forEach(
                (k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }

    //COMPUTE IF PRESENT
    public void usarComputeIfPresent() {
        Map<String, String> stMap = new HashMap<>();
        stMap.put("WY", "Cheyene");
        stMap.put("SD", "Pierre");
        stMap.put("CU", "Denver");
        stMap.computeIfPresent("WY",
                (k, v) -> v + " Verificado " + k + " ) ");
        System.out.println("Imprimir IfPresent");
        stMap.forEach(
                (k, v) -> System.out.println("Key: " + k + " value: " + v)
        );

    }

    public void usarMerge() {
        Map<String, String> stMap = new HashMap<>();
        stMap.put("WY", "Cheyene");
        stMap.put("SD", "Pierre");
        stMap.put("CU", "Denver");

        String mensaje = "Verificado";
        List<String> cList = stMap.keySet().stream()
                .filter(s -> s.startsWith("c"))
                .collect(Collectors.toList());

        cList.stream().forEach((e) -> {
            stMap.merge(e, mensaje, (s, n) -> s.concat(n));
        }
        );
        System.out.println("IMPRIMIR MERGER");
        stMap.forEach((k, v) -> System.out.println("Key: " + k + " value: " + v));

    }

    public void usarRemoveIf() {
        System.out.println("IMPRIMIR REMOVEIF");
        List<Integer> numeros = new ArrayList<Integer>();
        numeros.add(3);
        numeros.add(2);
        numeros.add(1);
        numeros.add(4);
        numeros.removeIf(i -> {
            return i >= 3;//siempre lleva return
        });
        System.out.println(numeros);
    }

    //REPLACE ALL USA EL UNARYOPERATOR
    public void usarReplaceAll() {

        System.out.println("Imprimir REPLACE ALL");
        Map<String, String> stMap = new HashMap<>();
        stMap.put("WY", "Cheyene");
        stMap.put("SD", "Pierre");
        stMap.put("CU", "Denver");

        stMap.replaceAll((a, b) -> a.length() + b);
        System.out.println(stMap);
    }
    
    public void usarMapKeyValuesConStream(){
        System.out.println("IMPRIMIR MAP (KEY, VALUE) CON STREAM");
        Map<String, String> stMap = new HashMap<>();
        stMap.put("WY", "Cheyene");
        stMap.put("SD", "Pierre");
        stMap.put("CU", "Denver");
        
        Set<String> stSet = stMap.keySet();
        Arrays.stream(stSet.toArray(new String [stSet.size()])).
                filter(s -> s.startsWith("C"))
                .forEach(s -> System.out.println("st: " + s));
        
        stMap.values().stream()
                .filter(s -> s.startsWith("D"))
                .forEach(s -> System.out.println("City: " + s));
    }
    
    public void usarMetodoStreamOf(){
        System.out.println("IMPRIMIR STREAM.OF");
        Stream.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
                .filter(s -> s.startsWith("L"))
                .forEach(s -> System.out.println("Resultados con L: " + s));
    }
//BUFFER READER.. EL METODO LINES() CONVIERTE A BUFFERREADER EN UN STREAM
    public void usarLineEnBufferReader(){
        System.out.println("IMPRIMIR LINE() USANDO BUFFEREDREADER");
        try(
            BufferedReader bReader = new BufferedReader(new FileReader("texto.txt"))){
            bReader.lines()
                    .forEach(line ->
                    System.out.println("Linea:" + line));
            
                    
        }catch(IOException e){
            System.out.println("Ocurrio un error con el archivo");
        }
    }
    //NIO CLASSSES / NIO FILE STREAM
    //El metodo lines puede ser llamado usando NIO classes
    public void usarNIOclasesEnFileStream(){
        System.out.println("----------------IMIPRIMIR FILES.LINES()---------");
        try(Stream<String> lines = 
                Files.lines(Paths.get("texto.txt")))
        {
            lines.forEach(line -> System.out.println("Lineas: " + line));
        }catch (IOException e){
            System.out.println("Ocurrio un error con el archivo");
        }
    } 
    
    //LEER UN ARCHIVO EN UN ARRAYLIST
    public void usarReadAllLines(){
        System.out.println("----------------IMIPRIMIR FILES.READALLLINES()---------");
        Path file = Paths.get("Texto.txt");
        List<String> fileArr;
        
        try{
            fileArr=Files.readAllLines(file);
            fileArr.stream()
                    .filter(line -> line.contains("PROSPERO"))
                    .forEach(line -> System.out.println(line));
            //GUARDA LAS LINEAS QUE CONTENGAN LA PALABRA PROSPERO
        }catch (IOException e){
            System.out.println("Exception");
        }
    }
    
    //Para listar el contenido de un directorio
    public void usarFileList(){
        System.out.println("----------------IMIPRIMIR FILES.LIST()---------");
        try(Stream<Path> files = Files.list(Paths.get("."))){
            files.
                    forEach(line -> System.out.println(line));
        }catch (IOException e){
            System.out.println("Exception");
        }
    }
    //WALKS DIRECTORY STRUCTURE
    
     public void usarFilesWalk(){
         System.out.println("----------------IMIPRIMIR FILES.WALK()---------");
         try(Stream <Path> files = Files.walk(Paths.get(".")) ){
             files
                     .forEach(line -> System.out.println(line));
         }catch (IOException e){
             System.out.println("Exception");
         }//MUESTRA TODOS LOS ARCHIVOS Y DIRECTORIOS QUE ESTAN EN LOS SUBDIRECTORIOS DE LA PRINCIPAL
     }
     //BUSCAR LA ESTRUCTURA DE UN DIRECTORIO, NIVEL 9
     public void usarFilesFind(){
         System.out.println("----------------IMIPRIMIR FILES.FIND()---------");
         try(Stream <Path> files = 
                 Files.find(Paths.get("."), 9, (p,a) -> a.isDirectory())){
             files.
                     forEach(f->System.out.println("Nombre del directorio: " + f));
         }catch(IOException e){
             System.out.println("Error");
         }
     }
     //Flatten Data with flatMap
     //use the flatmap method to flatten datain a stream
     public void usarFlatMap(){
         System.out.println("----------------IMIPRIMIR FLATMAP()---------");
         try{
         Path files = new File("texto.txt").toPath();
         long matches = Files.lines(files)
                 .flatMap(line -> Stream.of(line.split(" ")))
                 .filter(world -> world.contains("my"))
                 .peek(s -> System.out.println("Matchs: " + s))
                 .count();
         System.out.println("Number of matches: " + matches);
     }catch(IOException e){
         System.out.println("Exception");
     }
     }
    
    public static void main(String... args) {
        new tema8();
    }

}
