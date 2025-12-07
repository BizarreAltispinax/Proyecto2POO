package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas;

import java.io.Serializable;

public class Tarea implements Serializable {
    private int id;
    private String descripcion;

//    public Tarea(int id, String pDescripcion) {
//        this.id = id;
//        this.descripcion = pDescripcion;
//    }

    //Constructor para uso interno
    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

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
