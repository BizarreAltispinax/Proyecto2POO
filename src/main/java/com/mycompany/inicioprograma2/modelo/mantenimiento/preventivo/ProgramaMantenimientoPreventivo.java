package com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProgramaMantenimientoPreventivo implements Serializable {
    private List<Fase> fases;

    public ProgramaMantenimientoPreventivo() {
        this.fases = new ArrayList<>();
    }

    public void agregarFase(Fase fase) {
        fases.add(fase);
    }

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
