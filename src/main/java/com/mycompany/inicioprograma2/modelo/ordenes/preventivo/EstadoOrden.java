package com.mycompany.inicioprograma2.modelo.ordenes.preventivo;

import java.io.Serializable;

public enum EstadoOrden implements Serializable {
    PENDIENTE,
    EN_EJECUCION,
    TERMINADA,
    CANCELADA
}
