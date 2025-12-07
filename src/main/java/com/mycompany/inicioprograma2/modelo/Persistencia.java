/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.modelo;

import java.io.*;
import java.util.ArrayList;
/**
 * Clase encargada de realizar operaciones de persistencia mediante serialización.
 * Permite guardar y cargar listas u objetos individuales usando archivos .dat.
 * 
 * <p>Incluye varios métodos de guardado/carga para distintos escenarios
 * (listas, objetos simples, retornos null o vacíos, etc.).</p>
 */
public class Persistencia {
     /**
     * Guarda en un archivo una lista genérica serializable.
     *
     * @param <T> Tipo de los elementos de la lista.
     * @param nombreArchivo Ruta del archivo donde se guardará.
     * @param lista Lista a serializar.
     */
    public static <T> void guardar(String nombreArchivo, ArrayList<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error al guardar " + nombreArchivo + ": " + e.getMessage());
        }
    }
     /**
     * Guarda un objeto cualquiera en un archivo mediante serialización.
     *
     * @param nombreArchivo Ruta del archivo destino.
     * @param obj Objeto serializable a guardar.
     */
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
        /**
     * Carga una lista genérica desde un archivo.
     *
     * @param <T> Tipo de elementos que contiene la lista.
     * @param nombreArchivo Archivo desde el cual se leerá.
     * @return La lista cargada, o una nueva lista vacía si no existe o hubo error.
     */
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
    
     /**
     * Carga un objeto cualquiera desde un archivo.
     *
     * @param <T> Tipo esperado del objeto cargado.
     * @param nombreArchivo Ruta del archivo origen.
     * @return El objeto leído, o null si no existe o hubo error.
     */
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