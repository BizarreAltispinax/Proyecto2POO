package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import java.io.Serializable;

public class HistorialFallas implements Serializable {
    private int idFalla;
    private String causas;
    private String accionesTomadas;

    public HistorialFallas(int idFalla, String causas, String accionesTomadas) {
        this.idFalla = idFalla;
        this.causas = causas;
        this.accionesTomadas = accionesTomadas;
    }

    public int getIdFalla() { return idFalla; }

    public String getCausas() { return causas; }

    public void setCausas(String causas) { this.causas = causas; }

    public String getAccionesTomadas() { return accionesTomadas; }

    public void setAccionesTomadas(String accionesTomadas) { this.accionesTomadas = accionesTomadas; }

    @Override
    public String toString() {
        return "Falla ID: " + idFalla +
                "   Causas: " + causas +
                "   Acciones: " + accionesTomadas;
    }
}