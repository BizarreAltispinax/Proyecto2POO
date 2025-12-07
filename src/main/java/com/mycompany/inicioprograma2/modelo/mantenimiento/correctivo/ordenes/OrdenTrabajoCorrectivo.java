package com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Representa una orden de trabajo de mantenimiento correctivo.
 * <p>
 * Cada orden contiene información del equipo afectado, su estado,
 * fechas relevantes, costos, descripción del problema y datos del
 * proceso de diagnóstico y corrección.
 * </p>
 *
 * <p>Las órdenes se crean con un ID autoincremental y comienzan
 * en estado {@link EstadoOrdenCorrectiva#ABIERTA}.</p>
 */
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
    
    /**
     * Constructor principal de la orden correctiva.
     *
     * @param idEquipo ID del equipo afectado.
     * @param descripcionProblema Descripción del problema reportado.
     * @param prioridad Nivel de prioridad asignado.
     */
    public OrdenTrabajoCorrectivo(int idEquipo, String descripcionProblema, String prioridad) {
        this.id = contador++;
        this.idEquipo = idEquipo;
        this.descripcionProblema = descripcionProblema;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now();
        this.estado = EstadoOrdenCorrectiva.ABIERTA;
    }
    //Getter de ID
    public int getId() {
        return id;
    }
    //Getter de ID de un equipo
    public int getIdEquipo() {
        return idEquipo;
    }
    //Getter del estado
    public EstadoOrdenCorrectiva getEstado() {
        return estado;
    }

    public void iniciar(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.estado = EstadoOrdenCorrectiva.EN_PROCESO;
    }
     /**
     * Marca la orden como finalizada e ingresa los datos de cierre.
     *
     * @param fechaCierre Fecha de finalización.
     * @param horas Horas trabajadas.
     * @param manoObra Costo de mano de obra.
     * @param materiales Costo de materiales.
     * @param repuestos Repuestos empleados.
     * @param observaciones Notas adicionales.
     * @param causaRaiz Causa raíz identificada.
     * @param accionesTomadas Acciones realizadas para corregir.
     */
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
     /**
     * Cancela la orden y registra la fecha y motivo de la cancelación.
     *
     * @param fecha Fecha de cancelación.
     * @param razon Motivo por el cual se cancela la orden.
     */
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
