package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas;

import java.io.Serializable;
/**
 * Representa una tarea individual dentro de una fase de mantenimiento preventivo.
 * Cada tarea tiene un identificador y una descripción de lo que debe realizarse.
 */
public class Tarea implements Serializable {
    private int id;
    private String descripcion;

//    public Tarea(int id, String pDescripcion) {
//        this.id = id;
//        this.descripcion = pDescripcion;
//    }

    //Constructor para uso interno
     /**
     * Constructor principal para crear una tarea con ID y descripción.
     *
     * @param id          identificador único de la tarea
     * @param descripcion descripción de la tarea
     */
    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    //Getters y Setters de los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Descripcion: " + descripcion;
    }
}
