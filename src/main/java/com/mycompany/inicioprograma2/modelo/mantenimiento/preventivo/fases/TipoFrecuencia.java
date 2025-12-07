package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases;

import java.io.Serializable;
/**
 * Representa los tipos de frecuencia utilizados en las fases
 * de un programa de mantenimiento preventivo.
 *
 * <p>Estos valores indican la unidad de tiempo sobre la cual
 * se define la repetición o periodicidad de una fase.</p>
 */
public enum TipoFrecuencia implements Serializable {
    DIA,
    SEMANA,
    MES,
    AÑO
}
