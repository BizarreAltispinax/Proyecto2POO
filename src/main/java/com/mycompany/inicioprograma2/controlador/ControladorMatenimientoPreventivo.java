package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.ProgramaMantenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import java.util.List;

public class ControladorMatenimientoPreventivo {

    private final ProgramaMantenimientoPreventivo programa;

    public ControladorMatenimientoPreventivo() {
        this.programa = new ProgramaMantenimientoPreventivo();
    }

    public List<Fase> getFases() {
        return programa.getFases();
    }

    public boolean crearFase(TipoFrecuencia tipoFrecuencia, int medidorFrecuencia, int cantidadCiclos, String partes, String herramientas, String personal, float horasEstimadas) {
        try {
            Fase f = new Fase(tipoFrecuencia, medidorFrecuencia, cantidadCiclos, partes, herramientas, personal, horasEstimadas);
            programa.agregarFase(f);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarFase(int indice) {
        return programa.eliminarFase(indice);
    }

    public boolean agregarTareaAFase(int indiceFase, Tarea tarea) {
        Fase fase = programa.getFase(indiceFase);
        if (fase == null) return false;

        fase.agregarTarea(tarea);
        return true;
    }

    public ProgramaMantenimientoPreventivo getPrograma() {
        return programa;
    }
}
