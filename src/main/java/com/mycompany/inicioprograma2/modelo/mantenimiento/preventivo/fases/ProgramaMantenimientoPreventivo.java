package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un programa de mantenimiento preventivo compuesto por múltiples fases.
 * Cada fase indica una frecuencia, ciclos y tareas asociadas dentro del plan.
 * 
 * <p>Esta clase permite agregar, eliminar y obtener fases específicas.</p>
 *
 * @author 
 */
public class ProgramaMantenimientoPreventivo implements Serializable {
    private List<Fase> fases;
     /**
     * Crea un nuevo programa de mantenimiento preventivo con una lista vacía de fases.
     */
    public ProgramaMantenimientoPreventivo() {
        this.fases = new ArrayList<>();
    }
    
    /**
     * Agrega una nueva fase al programa.
     *
     * @param fase objeto {@link Fase} a agregar
     */
    public void agregarFase(Fase fase) {
        fases.add(fase);
    }
     /**
     * Elimina una fase según su índice.
     *
     * @param indice posición de la fase a eliminar
     * @return true si la fase fue eliminada correctamente, false si el índice es inválido
     */
    public boolean eliminarFase(int indice) {
        if (indice < 0 || indice >= fases.size())
            return false;
        fases.remove(indice);
        return true;
    }

    public Fase getFase(int indice) {
        if (indice < 0 || indice >= fases.size())
            return null;
        return fases.get(indice);
    }

    public List<Fase> getFases() {
        return fases;
    }

    @Override
    public String toString() {
        return  "Fases: " + fases.size();
    }
}
