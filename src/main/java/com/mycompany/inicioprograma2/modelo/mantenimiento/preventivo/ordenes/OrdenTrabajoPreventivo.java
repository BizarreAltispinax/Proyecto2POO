package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoPreventivo implements Serializable {
    private static int contador = 1;

    private final int id;
    private int idEquipo;
    private int indiceFase;

    private LocalDate fechaAgendada;
    private LocalDate fechaCreacion;

    private EstadoOrden estado;

    private LocalDate fechaInicioReal;

    private LocalDate fechaFinalizacionReal;
    private float horasTrabajadas;
    private int costoManoObra;
    private int costoMaterial;
    private String observaciones;

    private List<HistorialFallas> fallasObservadas;

    private LocalDate fechaCancelacion;
    private String razonDeCancelacion;

    public OrdenTrabajoPreventivo(int idEquipo, int indiceFase, LocalDate fechaAgendada) {
        this.id = contador++;
        this.idEquipo = idEquipo;
        this.indiceFase = indiceFase;
        this.fechaAgendada = fechaAgendada;
        this.fechaCreacion = LocalDate.now();
        this.estado = EstadoOrden.PENDIENTE;

        this.fallasObservadas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public int getIndiceFase() {
        return indiceFase;
    }

    public LocalDate getFechaAgendada() {
        return fechaAgendada;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public LocalDate getFechaInicioReal() {
        return fechaInicioReal;
    }

    public LocalDate getFechaFinalizacionReal() {
        return fechaFinalizacionReal;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public int getCostoManoObra() {
        return costoManoObra;
    }

    public int getCostoMaterial() {
        return costoMaterial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public String getRazonDeCancelacion() {
        return razonDeCancelacion;
    }

    public List<HistorialFallas> getFallasObservadas() {
        return fallasObservadas;
    }

    public void empezarOrden(LocalDate fechaInicio) {
        this.fechaInicioReal = fechaInicio;
        this.estado = EstadoOrden.EN_EJECUCION;
    }

    public void finalizarOrden(LocalDate fechaFinal, float horas, int costoManoObra, int costoMaterial, String observaciones) {
        this.fechaFinalizacionReal = fechaFinal;
        this.horasTrabajadas = horas;
        this.costoManoObra = costoManoObra;
        this.costoMaterial = costoMaterial;
        this.observaciones = observaciones;
        this.estado = EstadoOrden.TERMINADA;
    }

    public void cancelarOrden(LocalDate fechaCancelacion, String razon) {
        this.fechaCancelacion = fechaCancelacion;
        this.razonDeCancelacion = razon;
        this.estado = EstadoOrden.CANCELADA;
    }

    public void agregarFalla(HistorialFallas falla) {
        fallasObservadas.add(falla);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Equipo: " + idEquipo + "\n" +
                "Fase: " + indiceFase + "\n" +
                "Agendada: " + fechaAgendada + "\n" +
                "Estado: " + estado;
    }
}
