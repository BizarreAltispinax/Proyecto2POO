package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * Controlador encargado de gestionar la creación, modificación,
 * eliminación y carga visual de tareas de mantenimiento preventivo.
 * <p>
 * Las tareas se manejan en memoria y se almacenan en un archivo .dat
 * mediante la clase {@link Persistencia}.
 */
public class ControladorTarea {
    private final ArrayList<Tarea> tareas;
     /**
     * Constructor: carga la lista de tareas desde archivo.
     * Si el archivo no existe o está vacío, la lista será null
     * y eso deberá manejarse externamente si fuera necesario.
     */
    public ControladorTarea() {
        tareas = Persistencia.cargar("data/tareas.dat");
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
        Persistencia.guardar("data/tareas.dat", tareas);
    }

    //Impide que hayan "huecos" en los IDs
    private int obtenerIdLibre() {
        int id = 1;

        // crear un set de IDs usados
        Set<Integer> usados = new HashSet<>();
        for (Tarea t : tareas) {
            usados.add(t.getId());
        }

        // seguir aumentando mientras el ID exista
        while (usados.contains(id)) {
            id++;
        }

        return id;
    }
    //Agrega tareas
    public boolean agregarTarea(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) return false;

        int idAsignado = obtenerIdLibre();

        Tarea nueva = new Tarea(idAsignado, descripcion);
        tareas.add(nueva);

        return true;
    }
    //Modifica tareas
    public boolean modificarTarea(int id, String descripcion) {
        Tarea t = buscarPorId(id);
        if (t == null) return false;
        if (descripcion == null || descripcion.isBlank()) return false;

        t.setDescripcion(descripcion);
        return true;
    }
    //Elimina tareas
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
