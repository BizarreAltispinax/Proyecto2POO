package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import java.io.Serializable;

public enum EstadoOrden implements Serializable {
    PENDIENTE,
    EN_EJECUCION,
    TERMINADA,
    CANCELADA
}
