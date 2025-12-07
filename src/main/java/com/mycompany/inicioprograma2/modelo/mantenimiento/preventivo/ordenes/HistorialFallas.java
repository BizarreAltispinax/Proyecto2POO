package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes;

import java.io.Serializable;
/**
 * Representa el registro histórico de una falla asociada a una orden de
 * mantenimiento preventivo.
 *
 * <p>Permite almacenar las causas identificadas y las acciones tomadas para
 * atender la falla, facilitando trazabilidad y análisis futuro.</p>
 */
public class HistorialFallas implements Serializable {
    private int idFalla;
    private String causas;
    private String accionesTomadas;
     /**
     * Crea un historial de falla con la información básica.
     *
     * @param idFalla identificador de la falla asociada
     * @param causas descripción de las causas encontradas
     * @param accionesTomadas acciones realizadas para corregir o prevenir
     */
    public HistorialFallas(int idFalla, String causas, String accionesTomadas) {
        this.idFalla = idFalla;
        this.causas = causas;
        this.accionesTomadas = accionesTomadas;
    }
    //Getters y Setters de los atributos
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