package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import java.io.Serializable;
/**
 * Define los posibles estados de una orden de mantenimiento preventivo.
 *
 * <p>Estos valores permiten controlar el avance del flujo de trabajo
 * desde que la orden es creada hasta su cierre o cancelación.</p>
 */
public enum EstadoOrden implements Serializable {
    PENDIENTE,
    EN_EJECUCION,
    TERMINADA,
    CANCELADA
}
