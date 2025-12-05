package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import java.util.ArrayList;

public class ControladorProgramasPreventivos {
    private final ControladorEquipo ctrlEquipo;
    private final ControladorTarea ctrlTarea;
    private final ControladorOrdenPreventiva ctrlOrden;

    public ControladorProgramasPreventivos(ControladorEquipo e, ControladorTarea t, ControladorOrdenPreventiva o) {
        this.ctrlEquipo = e;
        this.ctrlTarea = t;
        this.ctrlOrden = o;
    }

    public ArrayList<Tarea> obtenerTareasDeEquipo(int equipoId) {
        ArrayList<Tarea> lista = new ArrayList<>();

        for (Tarea t : ctrlTarea.getTareas()) {
            if (t.getId() == equipoId) {
                lista.add(t);
            }
        }

        return lista;
    }

    public ArrayList<Equipos> equiposConMantenimiento() {
        ArrayList<Equipos> lista = new ArrayList<>();

        for (Equipos eq : ctrlEquipo.getEquipos()) {
            for (Tarea t : ctrlTarea.getTareas()) {
                if (t.getId() == eq.getId()) {
                    lista.add(eq);
                    break;
                }
            }
        }

        return lista;
    }
}
