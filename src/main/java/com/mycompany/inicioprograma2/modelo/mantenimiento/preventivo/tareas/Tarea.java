package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas;

import java.io.Serializable;

public class Tarea implements Serializable {
    private static int contador = 1;

    private int id;
    private String descripcion;

    public Tarea(String pDescripcion) {
        this.id = contador++;
        this.descripcion = pDescripcion;
    }

    //Constructor para uso interno
    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;

        if (id >= contador) {
            contador = id + 1;
        }
    }

    public int getId() {
        return id;
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
