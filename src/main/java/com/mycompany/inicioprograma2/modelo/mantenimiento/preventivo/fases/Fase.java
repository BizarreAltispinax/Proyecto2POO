package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fase implements Serializable {
    private TipoFrecuencia tipoFrecuencia;
    private int medidorFrecuencia;
    private int cantidadCiclos;

    private List<Tarea> tareas;
    private String partes;
    private String herramientas;
    private String personal;
    private float horasEstimadas;

    public Fase(TipoFrecuencia tipoFrecuencia, int medidorFrecuencia, int cantidadCiclos, String partes, String herramientas, String personal, float horasEstimadas) {
        this.tipoFrecuencia = tipoFrecuencia;
        this.medidorFrecuencia = medidorFrecuencia;
        this.cantidadCiclos = cantidadCiclos;
        this.tareas = new ArrayList<>();
        this.partes = partes;
        this.herramientas = herramientas;
        this.personal = personal;
        this.horasEstimadas = horasEstimadas;
    }

    public TipoFrecuencia getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(TipoFrecuencia tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public int getMedidorFrecuencia() {
        return medidorFrecuencia;
    }

    public void setMedidorFrecuencia(int medidorFrecuencia) {
        this.medidorFrecuencia = medidorFrecuencia;
    }

    public int getCantidadCiclos() {
        return cantidadCiclos;
    }

    public void setCantidadCiclos(int cantidadCiclos) {
        this.cantidadCiclos = cantidadCiclos;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    public String getPartes() {
        return partes;
    }

    public void setPartes(String partes) {
        this.partes = partes;
    }

    public String getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(String herramientas) {
        this.herramientas = herramientas;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public float getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(float horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    @Override
    public String toString() {
        return "tipoFrecuencia: " + tipoFrecuencia + "\n" +
                ", medidorFrecuencia: " + medidorFrecuencia + "\n" +
                ", cantidadCiclos: " + cantidadCiclos + "\n" +
                ", tareas: " + tareas.size();
    }
}
