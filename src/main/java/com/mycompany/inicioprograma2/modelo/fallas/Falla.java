package com.mycompany.inicioprograma2.modelo.fallas;

import java.io.Serializable;

public class Falla implements Serializable {
    private int contador = 1;

    private final int id;
    private String descripcion;
    private String tipo;        // eléctrico, mecánico, software, etc.
    private String criticidad;  // baja, media, alta

    public Falla(String descripcion, String tipo, String criticidad) {
        this.id = contador++;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.criticidad = criticidad;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCriticidad() {
        return criticidad;
    }

    public void setCriticidad(String criticidad) {
        this.criticidad = criticidad;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Descripcion: '" + descripcion + '\n' +
                "Tipo='" + tipo + '\n' +
                "Criticidad='" + criticidad;
    }
}