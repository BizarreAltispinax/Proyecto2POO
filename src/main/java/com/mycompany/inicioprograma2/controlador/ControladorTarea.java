package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.util.ArrayList;

public class ControladorTarea {
    private final ArrayList<Tarea> tareas;

    public ControladorTarea() {
        tareas = Persistencia.cargar("tareas.dat");
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public Tarea buscarPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) return t;
        }
        return null;
    }
    public void guardar() {
        Persistencia.guardar("tareas.dat", tareas);
    }
    public boolean agregarTarea(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) return false;

        Tarea nueva = new Tarea(descripcion);
        tareas.add(nueva);

        return true;
    }

    public boolean modificarTarea(int id, String descripcion) {
        Tarea t = buscarPorId(id);
        if (t == null) return false;
        if (descripcion == null || descripcion.isBlank()) return false;

        t.setDescripcion(descripcion);
        return true;
    }

    public boolean eliminarTarea(int id) {
        return tareas.removeIf(t -> t.getId() == id);
    }

    public void cargarTareasEn(JComboBox<String> combo) {
        combo.removeAllItems();
        for (Tarea t : tareas) {
            combo.addItem(t.getId() + " - " + t.getDescripcion());
        }
    }
}
