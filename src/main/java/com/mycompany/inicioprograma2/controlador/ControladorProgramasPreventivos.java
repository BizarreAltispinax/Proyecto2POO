package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.ProgramaMantenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;
import com.mycompany.inicioprograma2.modelo.Persistencia;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorProgramasPreventivos {
    //Id del equipo a programa preventivo
    private Map<Integer, ProgramaMantenimientoPreventivo> programas;
    private final ControladorEquipo ctrlEquipos;

    public ControladorProgramasPreventivos(ControladorEquipo ctrlEquipos) {
        this.ctrlEquipos = ctrlEquipos;
        

        this.programas = Persistencia.cargar3("programasPreventivos.dat");

        if (this.programas == null) {
            this.programas = new HashMap<>();
        }
    }
    public void guardar() {
        Persistencia.guardar3("programasPreventivos.dat", programas);
    }
    public boolean crearProgramaParaEquipo(int idEquipo) {
        Equipos eq = ctrlEquipos.buscarPorId(idEquipo);
        if (eq == null)
            return false;
        if (programas.containsKey(idEquipo))
            return false;

        programas.put(idEquipo, new ProgramaMantenimientoPreventivo());
        return true;
    }

    public ProgramaMantenimientoPreventivo getPrograma(int idEquipo) {
        return programas.get(idEquipo);
    }

    public List<Fase> getFases(int idEquipo) {
        ProgramaMantenimientoPreventivo p = programas.get(idEquipo);
        return p == null ? null : p.getFases();
    }

    public boolean crearFase(int idEquipo, TipoFrecuencia tipo, int medidor, int ciclos, String partes, String herramientas, String personal, float horas) {
        ProgramaMantenimientoPreventivo p = programas.get(idEquipo);
        if (p == null)
            return false;

        Fase f = new Fase(tipo, medidor, ciclos, partes, herramientas, personal, horas);
        p.agregarFase(f);
        return true;
    }

    public boolean eliminarFase(int idEquipo, int indiceFase) {
        ProgramaMantenimientoPreventivo p = programas.get(idEquipo);
        return p != null && p.eliminarFase(indiceFase);
    }

    public boolean modificarFase(
            int idEquipo,
            int indiceFase,
            TipoFrecuencia tipoFrecuencia,
            int medidorFrecuencia,
            int cantidadCiclos,
            String partes,
            String herramientas,
            String personal,
            float horasEstimadas
    ) {
        ProgramaMantenimientoPreventivo programa = programas.get(idEquipo);

        if (programa == null) {
            return false; // No existe programa para ese equipo
        }

        Fase fase = programa.getFase(indiceFase);
        if (fase == null) {
            return false; // No existe la fase
        }

        try {
            // Modificar campos
            fase.setTipoFrecuencia(tipoFrecuencia);
            fase.setMedidorFrecuencia(medidorFrecuencia);
            fase.setCantidadCiclos(cantidadCiclos);
            fase.setPartes(partes);
            fase.setHerramientas(herramientas);
            fase.setPersonal(personal);
            fase.setHorasEstimadas(horasEstimadas);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean agregarTareaAFase(int idEquipo, int indiceFase, Tarea tarea) {
        ProgramaMantenimientoPreventivo p = programas.get(idEquipo);
        if (p == null)
            return false;

        Fase fase = p.getFase(indiceFase);
        if (fase == null)
            return false;

        fase.agregarTarea(tarea);
        return true;
    }
}
