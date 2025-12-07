package com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes;

import java.io.Serializable;

public enum EstadoOrdenCorrectiva implements Serializable {
    ABIERTA,
    EN_PROCESO,
    FINALIZADA,
    CANCELADA
}
