package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.fallas.Falla;

import java.io.Serializable;

public class HistorialFallas implements Serializable {
    private Falla falla;
    private String causas;
    private String accionesTomadas;

    public HistorialFallas(Falla falla, String causas, String accionesTomadas) {
        this.falla = falla;
        this.causas = causas;
        this.accionesTomadas = accionesTomadas;
    }

    public Falla getFalla() {
        return falla;
    }

    /*
    public void setFalla(Falla falla) {
        this.falla = falla;
    }
    */

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getAccionesTomadas() {
        return accionesTomadas;
    }

    public void setAccionesTomadas(String accionesTomadas) {
        this.accionesTomadas = accionesTomadas;
    }

    @Override
    public String toString() {
        return "Falla: " + falla.getDescripcion() +
                " | Causas: " + causas +
                " | Actions: " + accionesTomadas;
    }
}
