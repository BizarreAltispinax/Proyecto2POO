/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.modelo;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {

    public static <T> void guardar(String nombreArchivo, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error al guardar " + nombreArchivo + ": " + e.getMessage());
        }
    }
    
    public static void guardar2(String nombreArchivo, Object obj) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
        oos.writeObject(obj);
    } catch (Exception e) {
        System.out.println("Error al guardar " + nombreArchivo + ": " + e.getMessage());
    }
}
    
   public static void guardar3(String archivo, Object objeto) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
        oos.writeObject(objeto);
    } catch (Exception e) {
        System.out.println("Error al guardar " + archivo + ": " + e.getMessage());
    }
} 

    public static <T> ArrayList<T> cargar(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // si no existe, retorna lista vacía
        } catch (Exception e) {
            System.out.println("Error al cargar " + nombreArchivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    
    public static <T> T cargar2(String nombreArchivo) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
        return (T) ois.readObject();
    } catch (FileNotFoundException e) {
        return null; 
    } catch (Exception e) {
        System.out.println("Error al cargar " + nombreArchivo + ": " + e.getMessage());
        return null;
    }
    
}
    public static <T> T cargar3(String archivo) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        return (T) ois.readObject();
    } catch (FileNotFoundException e) {
        return null;
    } catch (Exception e) {
        System.out.println("Error al cargar " + archivo + ": " + e.getMessage());
        return null;
    }
}
    
}