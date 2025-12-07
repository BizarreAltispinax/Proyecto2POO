package com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.fallas;

import java.io.Serializable;

public class Falla implements Serializable {

    private int idEquipo;
    private int id;
    private String descripcion;

    public Falla(int idEquipo, int id, String descripcion) {
        this.idEquipo = idEquipo;
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getIdEquipo() {
        return idEquipo;
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
}