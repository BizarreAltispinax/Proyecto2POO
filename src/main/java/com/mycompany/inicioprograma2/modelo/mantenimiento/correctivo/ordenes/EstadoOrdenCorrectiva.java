package com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes;

import java.io.Serializable;
/**
 * Representa los distintos estados que puede tener una orden de mantenimiento correctivo.
 * <p>
 * Este enum es serializable para permitir que su valor se almacene en archivos
 * junto con objetos que lo contengan.
 * </p>
 *
 * <ul>
 *     <li><b>ABIERTA:</b> La orden ha sido creada, pero aún no ha empezado a trabajarse.</li>
 *     <li><b>EN_PROCESO:</b> El personal ya está realizando el correctivo.</li>
 *     <li><b>FINALIZADA:</b> El trabajo correctivo ha sido completado.</li>
 *     <li><b>CANCELADA:</b> La orden fue anulada y no se ejecutará.</li>
 * </ul>
 */
public enum EstadoOrdenCorrectiva implements Serializable {
    ABIERTA,
    EN_PROCESO,
    FINALIZADA,
    CANCELADA
}
