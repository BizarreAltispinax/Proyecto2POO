package com.mycompany.inicioprograma2.modelo.mantenimiento.fallas;

import java.io.Serializable;
/**
 * Representa una falla registrada en un equipo.
 * <p>
 * Cada falla contiene un identificador propio, el ID del equipo
 * al que pertenece y una descripción de la falla observada.
 * </p>
 */
public class Falla implements Serializable {

    private int idEquipo;
    private int id;
    private String descripcion;
     /**
     * Constructor principal de la falla.
     *
     * @param idEquipo ID del equipo asociado.
     * @param id ID único de la falla.
     * @param descripcion Descripción de la falla.
     */
    public Falla(int idEquipo, int id, String descripcion) {
        this.idEquipo = idEquipo;
        this.id = id;
        this.descripcion = descripcion;
    }
    //Get id de un equipo
    public int getIdEquipo() {
        return idEquipo;
    }
    //Get del ID
    public int getId() {
        return id;
    }
    //Get de la descripcion
    public String getDescripcion() {
        return descripcion;
    }
    //Set de la descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}