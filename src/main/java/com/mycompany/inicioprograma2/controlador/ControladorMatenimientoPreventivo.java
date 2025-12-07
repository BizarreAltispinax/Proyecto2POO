package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.ProgramaMantenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;
import com.mycompany.inicioprograma2.modelo.Persistencia;
import java.util.List;
/**
* Controlador para gestionar el programa de mantenimiento preventivo.
* Se encarga de cargar, modificar y guardar las fases y sus tareas.
*/
public class ControladorMatenimientoPreventivo {

    private ProgramaMantenimientoPreventivo programa;
    /**
    * Constructor que carga el programa desde archivo.
    * Si no existe, crea uno nuevo.
    */
    public ControladorMatenimientoPreventivo() {
        this.programa = Persistencia.cargar2("data/mantenimientoPreventivo.dat");

        if (this.programa == null) {
            this.programa = new ProgramaMantenimientoPreventivo();
        }
    }

    public List<Fase> getFases() {
        return programa.getFases();
    }
    public void guardar() {
        Persistencia.guardar2("data/mantenimientoPreventivo.dat", programa);
    }
    /**
    * Crea y agrega una nueva fase al programa.
    * @param tipoFrecuencia tipo de frecuencia asignado
    * @param medidorFrecuencia valor del medidor
    * @param cantidadCiclos ciclos asociados
    * @param partes partes involucradas
    * @param herramientas herramientas necesarias
    * @param personal personal requerido
    * @param horasEstimadas horas estimadas de trabajo
    * @return true si se agregó correctamente
    */
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
