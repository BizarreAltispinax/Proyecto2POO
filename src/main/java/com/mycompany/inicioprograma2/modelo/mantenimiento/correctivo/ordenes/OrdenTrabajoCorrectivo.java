package com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes;

import java.io.Serializable;
import java.time.LocalDate;

public class OrdenTrabajoCorrectivo implements Serializable {
    private static int contador = 1;

    private final int id;
    private int idEquipo;
    private LocalDate fechaCreacion;
    private String descripcionProblema;
    private String prioridad;

    private EstadoOrdenCorrectiva estado;

    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;

    private float horasTrabajadas;
    private int costoManoObra;
    private int costoMateriales;
    private String repuestosUtilizados;
    private String observaciones;

    private String causaRaiz;
    private String accionesTomadas;

    public OrdenTrabajoCorrectivo(int idEquipo, String descripcionProblema, String prioridad) {
        this.id = contador++;
        this.idEquipo = idEquipo;
        this.descripcionProblema = descripcionProblema;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now();
        this.estado = EstadoOrdenCorrectiva.ABIERTA;
    }

    public int getId() {
        return id;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public EstadoOrdenCorrectiva getEstado() {
        return estado;
    }

    public void iniciar(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.estado = EstadoOrdenCorrectiva.EN_PROCESO;
    }

    public void finalizar(LocalDate fechaCierre, float horas, int manoObra, int materiales,
                          String repuestos, String observaciones,
                          String causaRaiz, String accionesTomadas) {

        this.fechaFinalizacion = fechaCierre;
        this.horasTrabajadas = horas;
        this.costoManoObra = manoObra;
        this.costoMateriales = materiales;
        this.repuestosUtilizados = repuestos;
        this.observaciones = observaciones;
        this.causaRaiz = causaRaiz;
        this.accionesTomadas = accionesTomadas;

        this.estado = EstadoOrdenCorrectiva.FINALIZADA;
    }

    public void cancelar(LocalDate fecha, String razon) {
        this.fechaFinalizacion = fecha;
        this.observaciones = razon;
        this.estado = EstadoOrdenCorrectiva.CANCELADA;
    }

    @Override
    public String toString() {
        return  "Orden Correctiva " + id +
                "\nEquipo: " + idEquipo +
                "\nEstado: " + estado +
                "\nDescripción: " + descripcionProblema +
                "\nPrioridad: " + prioridad +
                "\nCreada: " + fechaCreacion;
    }
}
