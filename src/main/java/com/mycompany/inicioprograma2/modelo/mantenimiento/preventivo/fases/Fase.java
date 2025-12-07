package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Representa una fase dentro de un plan de mantenimiento preventivo.
 * <p>
 * Una fase define su frecuencia, cantidad de ciclos, las tareas asociadas
 * y los recursos necesarios tales como partes, herramientas, personal
 * y horas estimadas.
 * </p>
 */
public class Fase implements Serializable {
    private TipoFrecuencia tipoFrecuencia;
    private int medidorFrecuencia;
    private int cantidadCiclos;

    private List<Tarea> tareas;
    private String partes;
    private String herramientas;
    private String personal;
    private float horasEstimadas;
     /**
     * Constructor que inicializa una fase con sus parámetros básicos.
     *
     * @param tipoFrecuencia Tipo de frecuencia (día, mes, año, etc.).
     * @param medidorFrecuencia Cantidad asociada al tipo de frecuencia.
     * @param cantidadCiclos Número de repeticiones según la frecuencia.
     * @param partes Partes requeridas para la fase.
     * @param herramientas Herramientas necesarias.
     * @param personal Personal encargado.
     * @param horasEstimadas Horas estimadas de trabajo.
     */
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
    //Getters y Setters de los atributos
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
        /**
     * Genera un texto explicativo legible sobre cómo se ejecuta la fase.
     *
     * @param numero Número de la fase dentro del plan.
     * @param fase Fase a formatear.
     * @return Descripción textual formateada.
     */
    public static String formatearFase(int numero, Fase fase) {
        int medidor = fase.getMedidorFrecuencia();
        int ciclos = fase.getCantidadCiclos();
        String tipo = fase.getTipoFrecuencia().toString().toLowerCase();

        //Convertir pluralidad
        String unidad;
        
        switch (tipo) {
            case "año": unidad = (medidor == 1) ? "año" : "años"; break;
            case "mes": unidad = (medidor == 1) ? "mes" : "meses"; break;
            case "semana": unidad = (medidor == 1) ? "semana" : "semanas"; break;
            case "dia": unidad = (medidor == 1) ? "día" : "días"; break;
            default: unidad = tipo;
        }

        //Si el medidor es cero, se hace un formato especial
        if (ciclos == 0) {
            if (medidor > 1) {
                return "- Fase " + numero +
                        ": se hace cada " + medidor + " " + unidad +
                        " durante el tiempo que esté funcionando el equipo";
            } else {
                return "- Fase " + numero +
                        ": se hace cada " + unidad +
                        " durante el tiempo que esté funcionando el equipo";
            }
        }

        //Pluralizar "vez"
        String palabraCiclos = (ciclos == 1) ? "vez" : "veces";

        if (medidor > 1) {
            return "- Fase " + numero +
                    ": se hace cada " + medidor + " " + unidad +
                    ", se repite " + ciclos + " " + palabraCiclos;
        } else {
            return "- Fase " + numero +
                    ": se hace cada " + unidad +
                    ", se repite " + ciclos + " " + palabraCiclos;
        }
    }

    @Override
    public String toString() {
        return "tipoFrecuencia: " + tipoFrecuencia + "\n" +
                ", medidorFrecuencia: " + medidorFrecuencia + "\n" +
                ", cantidadCiclos: " + cantidadCiclos + "\n" +
                ", tareas: " + tareas.size();
    }
}
